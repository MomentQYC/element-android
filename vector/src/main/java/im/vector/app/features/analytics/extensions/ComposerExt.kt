/*
 * Copyright 2022-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package im.vector.app.features.analytics.extensions

import im.vector.app.features.analytics.plan.Composer
import im.vector.app.features.home.room.detail.composer.MessageComposerViewState
import im.vector.app.features.home.room.detail.composer.SendMode

fun MessageComposerViewState.toAnalyticsComposer(): Composer =
        Composer(
                inThread = isInThreadTimeline(),
                isEditing = sendMode is SendMode.Edit,
                isReply = sendMode is SendMode.Reply,
                messageType = Composer.MessageType.Text,
                startsThread = startsThread,
        )