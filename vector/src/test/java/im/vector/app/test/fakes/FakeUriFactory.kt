/*
 * Copyright 2022-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-Element-Commercial
 * Please see LICENSE files in the repository root for full details.
 */

package im.vector.app.test.fakes

import im.vector.app.features.onboarding.UriFactory
import io.mockk.every
import io.mockk.mockk

class FakeUriFactory {

    val instance = mockk<UriFactory>().also {
        every { it.parse(any()) } answers {
            val input = it.invocation.args.first() as String
            FakeUri().also { it.givenEquals(input) }.instance
        }
    }
}
