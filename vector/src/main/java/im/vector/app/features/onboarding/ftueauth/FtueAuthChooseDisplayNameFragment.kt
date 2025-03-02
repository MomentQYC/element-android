/*
 * Copyright 2021-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-Element-Commercial
 * Please see LICENSE files in the repository root for full details.
 */

package im.vector.app.features.onboarding.ftueauth

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import dagger.hilt.android.AndroidEntryPoint
import im.vector.app.core.extensions.hasContent
import im.vector.app.core.platform.SimpleTextWatcher
import im.vector.app.databinding.FragmentFtueDisplayNameBinding
import im.vector.app.features.onboarding.OnboardingAction
import im.vector.app.features.onboarding.OnboardingViewEvents
import im.vector.app.features.onboarding.OnboardingViewState

@AndroidEntryPoint
class FtueAuthChooseDisplayNameFragment :
        AbstractFtueAuthFragment<FragmentFtueDisplayNameBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFtueDisplayNameBinding {
        return FragmentFtueDisplayNameBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        views.displayNameInput.editText?.addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(s: Editable) {
                val newContent = s.toString()
                views.displayNameSubmit.isEnabled = newContent.isNotEmpty()
            }
        })
        views.displayNameInput.editText?.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    updateDisplayName()
                    true
                }
                else -> false
            }
        }

        views.displayNameSubmit.debouncedClicks { updateDisplayName() }
        views.displayNameSkip.debouncedClicks { viewModel.handle(OnboardingAction.UpdateDisplayNameSkipped) }
    }

    private fun updateDisplayName() {
        val newDisplayName = views.displayNameInput.editText?.text.toString()
        viewModel.handle(OnboardingAction.UpdateDisplayName(newDisplayName))
    }

    override fun updateWithState(state: OnboardingViewState) {
        views.displayNameInput.editText?.setText(state.personalizationState.displayName)
        views.displayNameSubmit.isEnabled = views.displayNameInput.hasContent()
    }

    override fun resetViewModel() {
        // Nothing to do
    }

    override fun onBackPressed(toolbarButton: Boolean): Boolean {
        viewModel.handle(OnboardingAction.PostViewEvent(OnboardingViewEvents.OnTakeMeHome))
        return true
    }
}
