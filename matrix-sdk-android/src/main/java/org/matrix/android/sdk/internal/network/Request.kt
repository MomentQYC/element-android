/*
 * Copyright 2020 The Matrix.org Foundation C.I.C.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.matrix.android.sdk.internal.network

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import org.matrix.android.sdk.api.failure.Failure
import org.matrix.android.sdk.api.failure.GlobalError
import org.matrix.android.sdk.api.failure.MatrixError
import org.matrix.android.sdk.api.failure.getRetryDelay
import org.matrix.android.sdk.api.failure.shouldBeRetried
import org.matrix.android.sdk.internal.network.ssl.CertUtil
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

/**
 * Hold the retry policy for a request.
 *
 * @property canRetry whether the request can be retried.
 * @property delay a fixed delay (in ms) before retrying the request. Set null to let the sdk apply its own delay.
 */
internal data class RequestRetryPolicy(
        val canRetry: Boolean = false,
        val delay: Long? = null
)

/**
 * Default retry policy according to the given error.
 */
internal val defaultRequestRetryPolicy = { throwable: Throwable ->
    when {
        // By default, systematically retry on 429
        (throwable is Failure.ServerError && throwable.httpCode == 429 && throwable.error.code == MatrixError.M_LIMIT_EXCEEDED) -> {
            RequestRetryPolicy(true, throwable.getRetryDelay(1000L))
        }
        throwable.shouldBeRetried()                                                                                             -> {
            RequestRetryPolicy(true)
        }
        else                                                                                                                    -> RequestRetryPolicy()
    }
}

/**
 * Execute a request from the requestBlock and handle some of the Exception it could generate.
 * Ref: https://github.com/matrix-org/matrix-js-sdk/blob/develop/src/scheduler.js#L138-L175
 *
 * @param globalErrorReceiver will be use to notify error such as invalid token error. See [GlobalError]
 * @param maxDelayBeforeRetry the max delay to wait before a retry
 * @param maxRetriesCount the max number of retries
 * @param getRequestRetryPolicy tell if the request can be executed again according to the given error, after a delay
 * @param requestBlock a suspend lambda to perform the network request
 */
internal suspend inline fun <DATA> executeRequest(globalErrorReceiver: GlobalErrorReceiver?,
                                                  maxDelayBeforeRetry: Long = 32_000L,
                                                  maxRetriesCount: Int = 4,
                                                  noinline getRequestRetryPolicy: (throwable: Throwable) -> RequestRetryPolicy = defaultRequestRetryPolicy,
                                                  noinline requestBlock: suspend () -> DATA): DATA {
    var currentRetryCount = 0
    var currentDelay = 1_000L

    while (true) {
        try {
            return requestBlock()
        } catch (throwable: Throwable) {
            val exception = when (throwable) {
                is KotlinNullPointerException -> IllegalStateException("The request returned a null body")
                is HttpException              -> throwable.toFailure(globalErrorReceiver)
                else                          -> throwable
            }

            // Log some details about the request which has failed.
            val request = (throwable as? HttpException)?.response()?.raw()?.request
            if (request == null) {
                Timber.e("Exception when executing request")
            } else {
                Timber.e("Exception when executing request ${request.method} ${request.url.toString().substringBefore("?")}")
            }

            // Check if this is a certificateException
            CertUtil.getCertificateException(exception)
                    // TODO Support certificate error once logged
                    // ?.also { unrecognizedCertificateException ->
                    //    // Send the error to the bus, for a global management
                    //    eventBus?.post(GlobalError.CertificateError(unrecognizedCertificateException))
                    // }
                    ?.also { unrecognizedCertificateException -> throw unrecognizedCertificateException }

            currentRetryCount++

            val retryPolicy = getRequestRetryPolicy(exception)
            if (retryPolicy.canRetry && currentRetryCount < maxRetriesCount) {
                delay(retryPolicy.delay ?: currentDelay)
                currentDelay = currentDelay.times(2L).coerceAtMost(maxDelayBeforeRetry)
                // Try again (loop)
            } else {
                throw when (exception) {
                    is IOException           -> Failure.NetworkConnection(exception)
                    is Failure.ServerError,
                    is Failure.OtherServerError,
                    is CancellationException -> exception
                    else                     -> Failure.Unknown(exception)
                }
            }
        }
    }
}
