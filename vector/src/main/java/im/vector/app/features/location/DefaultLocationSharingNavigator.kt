/*
 * Copyright 2022-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package im.vector.app.features.location

import android.app.Activity

class DefaultLocationSharingNavigator constructor(val activity: Activity?) : LocationSharingNavigator {

    override fun quit() {
        activity?.finish()
    }
}