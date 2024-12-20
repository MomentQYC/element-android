/*
 * Copyright 2020-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package im.vector.app.features.createdirect

import im.vector.app.core.platform.VectorViewEvents

sealed class CreateDirectRoomViewEvents : VectorViewEvents {
    object InvalidCode : CreateDirectRoomViewEvents()
    object DmSelf : CreateDirectRoomViewEvents()
}