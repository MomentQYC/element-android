/*
 * Copyright 2021-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package im.vector.app.core.ui.list

import im.vector.app.core.epoxy.ClickListener

data class Action(
        val title: String,
        val listener: ClickListener
)