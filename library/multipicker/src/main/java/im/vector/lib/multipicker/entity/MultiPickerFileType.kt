/*
 * Copyright 2020-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package im.vector.lib.multipicker.entity

import android.net.Uri

data class MultiPickerFileType(
        override val displayName: String?,
        override val size: Long,
        override val mimeType: String?,
        override val contentUri: Uri
) : MultiPickerBaseType