/*
 * Copyright 2022-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package im.vector.app.features.pin.lockscreen.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PinCodeKeyAlias

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BiometricKeyAlias