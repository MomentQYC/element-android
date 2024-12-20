/*
 * Copyright 2019-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package im.vector.app.core.platform

interface VectorViewModelAction

/**
 * To use when no action is associated to the ViewModel.
 */
object EmptyAction : VectorViewModelAction