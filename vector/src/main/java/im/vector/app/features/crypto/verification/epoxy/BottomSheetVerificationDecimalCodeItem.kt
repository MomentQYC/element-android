/*
 * Copyright 2020-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-Element-Commercial
 * Please see LICENSE files in the repository root for full details.
 */
package im.vector.app.features.crypto.verification.epoxy

import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import im.vector.app.R
import im.vector.app.core.epoxy.VectorEpoxyHolder
import im.vector.app.core.epoxy.VectorEpoxyModel

/**
 * A action for bottom sheet.
 */
@EpoxyModelClass
abstract class BottomSheetVerificationDecimalCodeItem : VectorEpoxyModel<BottomSheetVerificationDecimalCodeItem.Holder>(
        R.layout.item_verification_decimal_code
) {

    @EpoxyAttribute
    var code: String = ""

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.code.text = code
    }

    class Holder : VectorEpoxyHolder() {
        val code by bind<TextView>(R.id.itemVerificationDecimalCode)
    }
}
