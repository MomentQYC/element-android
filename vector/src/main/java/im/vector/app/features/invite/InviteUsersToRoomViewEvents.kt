/*
 * Copyright 2020-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package im.vector.app.features.invite

import im.vector.app.core.platform.VectorViewEvents

sealed class InviteUsersToRoomViewEvents : VectorViewEvents {
    object Loading : InviteUsersToRoomViewEvents()
    data class Failure(val throwable: Throwable) : InviteUsersToRoomViewEvents()
    data class Success(val successMessage: String) : InviteUsersToRoomViewEvents()
}