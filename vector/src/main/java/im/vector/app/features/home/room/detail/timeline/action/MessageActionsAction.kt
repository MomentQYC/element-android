/*
 * Copyright 2019-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package im.vector.app.features.home.room.detail.timeline.action

import im.vector.app.core.platform.VectorViewModelAction

sealed class MessageActionsAction : VectorViewModelAction {
    object ToggleReportMenu : MessageActionsAction()
}