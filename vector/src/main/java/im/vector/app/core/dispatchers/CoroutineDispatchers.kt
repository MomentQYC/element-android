/*
 * Copyright 2021-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package im.vector.app.core.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

data class CoroutineDispatchers @Inject constructor(
        val io: CoroutineDispatcher,
        val computation: CoroutineDispatcher
)