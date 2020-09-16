/*
 * Copyright (c) 2020 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.app.features.call

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import org.matrix.android.sdk.api.session.call.CallState
import org.matrix.android.sdk.api.util.MatrixItem

data class VectorCallViewState(
        val callId: String? = null,
        val roomId: String = "",
        val isVideoCall: Boolean,
        val isAudioMuted: Boolean = false,
        val isVideoEnabled: Boolean = true,
        val isVideoCaptureInError: Boolean = false,
        val isHD: Boolean = false,
        val isFrontCamera: Boolean = true,
        val canSwitchCamera: Boolean = true,
        val soundDevice: CallAudioManager.SoundDevice = CallAudioManager.SoundDevice.PHONE,
        val availableSoundDevices: List<CallAudioManager.SoundDevice> = emptyList(),
        val otherUserMatrixItem: Async<MatrixItem> = Uninitialized,
        val callState: Async<CallState> = Uninitialized
) : MvRxState
