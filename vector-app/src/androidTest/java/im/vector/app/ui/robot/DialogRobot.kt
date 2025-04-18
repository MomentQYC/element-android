/*
 * Copyright 2021-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-Element-Commercial
 * Please see LICENSE files in the repository root for full details.
 */

package im.vector.app.ui.robot

import com.adevinta.android.barista.interaction.BaristaDialogInteractions.clickDialogNegativeButton

class DialogRobot(
        var returnedToPreviousScreen: Boolean = false
) {

    fun negativeAction() {
        clickDialogNegativeButton()
        returnedToPreviousScreen = true
    }
}
