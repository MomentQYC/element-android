/*
 * Copyright 2021-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package im.vector.app.features.spaces.share

import im.vector.app.core.platform.VectorViewEvents

sealed class ShareSpaceViewEvents : VectorViewEvents {
    data class NavigateToInviteUser(val spaceId: String) : ShareSpaceViewEvents()
    data class ShowInviteByLink(val permalink: String, val spaceName: String) : ShareSpaceViewEvents()
}
