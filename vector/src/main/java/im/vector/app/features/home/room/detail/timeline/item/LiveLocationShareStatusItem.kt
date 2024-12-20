/*
 * Copyright 2022-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package im.vector.app.features.home.room.detail.timeline.item

import android.widget.ImageView
import im.vector.app.features.home.room.detail.timeline.style.TimelineMessageLayout

interface LiveLocationShareStatusItem {
    fun bindMap(
            mapImageView: ImageView,
            mapWidth: Int,
            mapHeight: Int,
            messageLayout: TimelineMessageLayout
    )

    fun bindBottomBanner(bannerImageView: ImageView, messageLayout: TimelineMessageLayout)
}