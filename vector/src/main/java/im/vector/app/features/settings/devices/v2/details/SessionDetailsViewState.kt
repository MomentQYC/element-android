/*
 * Copyright 2022-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package im.vector.app.features.settings.devices.v2.details

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import im.vector.app.features.settings.devices.v2.DeviceFullInfo

data class SessionDetailsViewState(
        val deviceId: String,
        val deviceFullInfo: Async<DeviceFullInfo> = Uninitialized,
) : MavericksState {
    constructor(args: SessionDetailsArgs) : this(
            deviceId = args.deviceId
    )
}