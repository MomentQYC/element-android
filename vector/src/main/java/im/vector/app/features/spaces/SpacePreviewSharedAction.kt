/*
 * Copyright 2021-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package im.vector.app.features.spaces

import im.vector.app.core.platform.VectorSharedAction

sealed class SpacePreviewSharedAction : VectorSharedAction {
    object DismissAction : SpacePreviewSharedAction()
    object ShowModalLoading : SpacePreviewSharedAction()
    object HideModalLoading : SpacePreviewSharedAction()
    data class ShowErrorMessage(val error: String? = null) : SpacePreviewSharedAction()
}