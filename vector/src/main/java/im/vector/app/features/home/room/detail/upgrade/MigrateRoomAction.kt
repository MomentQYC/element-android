/*
 * Copyright 2021-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package im.vector.app.features.home.room.detail.upgrade

import im.vector.app.core.platform.VectorViewModelAction

sealed class MigrateRoomAction : VectorViewModelAction {
    data class SetAutoInvite(val autoInvite: Boolean) : MigrateRoomAction()
    data class SetUpdateKnownParentSpace(val update: Boolean) : MigrateRoomAction()
    object UpgradeRoom : MigrateRoomAction()
}