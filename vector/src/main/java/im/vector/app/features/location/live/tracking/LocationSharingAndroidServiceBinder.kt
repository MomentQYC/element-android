/*
 * Copyright 2022-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package im.vector.app.features.location.live.tracking

import android.os.Binder

class LocationSharingAndroidServiceBinder : Binder() {

    private var locationSharingAndroidService: LocationSharingAndroidService? = null

    fun setup(service: LocationSharingAndroidService) {
        locationSharingAndroidService = service
    }

    fun cleanUp() {
        locationSharingAndroidService = null
    }

    fun getService() = locationSharingAndroidService
}