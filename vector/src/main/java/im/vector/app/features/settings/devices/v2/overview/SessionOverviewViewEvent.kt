/*
 * Copyright 2022-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package im.vector.app.features.settings.devices.v2.overview

import im.vector.app.core.platform.VectorViewEvents
import org.matrix.android.sdk.api.auth.registration.RegistrationFlowResponse

sealed class SessionOverviewViewEvent : VectorViewEvents {
    object ShowVerifyCurrentSession : SessionOverviewViewEvent()
    data class ShowVerifyOtherSession(val deviceId: String) : SessionOverviewViewEvent()
    object PromptResetSecrets : SessionOverviewViewEvent()
    data class RequestReAuth(
            val registrationFlowResponse: RegistrationFlowResponse,
            val lastErrorCode: String?
    ) : SessionOverviewViewEvent()

    object SignoutSuccess : SessionOverviewViewEvent()
    data class SignoutError(val error: Throwable) : SessionOverviewViewEvent()
}