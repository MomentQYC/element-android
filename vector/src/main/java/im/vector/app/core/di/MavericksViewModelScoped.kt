/*
 * Copyright 2021-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package im.vector.app.core.di

import javax.inject.Scope

/**
 * Scope annotation for bindings that should exist for the life of an MavericksViewModel.
 */
@Scope
annotation class MavericksViewModelScoped