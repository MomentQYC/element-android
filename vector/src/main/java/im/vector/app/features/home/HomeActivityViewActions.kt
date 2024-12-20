/*
 * Copyright 2020-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package im.vector.app.features.home

import im.vector.app.core.platform.VectorViewModelAction

sealed interface HomeActivityViewActions : VectorViewModelAction {
    object ViewStarted : HomeActivityViewActions
    object PushPromptHasBeenReviewed : HomeActivityViewActions
    data class RegisterPushDistributor(val distributor: String) : HomeActivityViewActions
}