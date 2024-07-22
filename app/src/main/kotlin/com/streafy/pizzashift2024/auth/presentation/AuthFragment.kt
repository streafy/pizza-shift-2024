package com.streafy.pizzashift2024.auth.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.streafy.pizzashift2024.R
import com.streafy.pizzashift2024.databinding.FragmentAuthBinding
import com.streafy.pizzashift2024.shared.disable
import com.streafy.pizzashift2024.shared.enable
import com.streafy.pizzashift2024.shared.gone
import com.streafy.pizzashift2024.shared.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthFragment : Fragment() {

    companion object {
        fun newInstance() = AuthFragment()
    }

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupOnClickListeners()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest(::handleState)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupOnClickListeners() {
        with(binding) {
            btnContinue.setOnClickListener {
                viewModel.requestOtpCode()
            }
            btnLogin.setOnClickListener {
                viewModel.signIn()
            }
            btnRequestCodeAgain.setOnClickListener {
                viewModel.requestOtpCode()
            }
            ivCloseButton.setOnClickListener {
                viewModel.onCloseButtonClick()
            }
            ivBackButton.setOnClickListener {
                viewModel.onBackButtonClick()
            }
            etPhone.doOnTextChanged { text, _, _, _ ->
                viewModel.onPhoneChanged(text.toString())
            }
            etCode.doOnTextChanged { text, _, _, _ ->
                viewModel.onCodeChanged(text.toString())
            }
        }
    }

    private fun handleState(state: AuthUiState) {
        with(binding) {
            showContent(state.phone, state.code, state.timeout)
            if (state.isLoading) {
                showLoading()
            }
            if (state.isError) {
                val requestedCode = state.timeout != null
                showError(state.errorMessage, requestedCode)
            }
        }
    }

    private fun FragmentAuthBinding.showLoading() {
        cpiLoading.visible()

        etPhone.disable()
        etCode.disable()
        btnContinue.disable()
        btnLogin.disable()
        btnRequestCodeAgain.disable()
    }

    private fun FragmentAuthBinding.showError(message: String?, requestedCode: Boolean) {
        Snackbar.make(
            binding.root,
            message ?: getString(R.string.unknown_error),
            Snackbar.LENGTH_SHORT
        ).show()
        viewModel.dismissError()
        toggleInputsEnabledState(requestedCode)
    }

    private fun FragmentAuthBinding.showContent(phone: String, code: String?, timeout: Int?) {
        cpiLoading.gone()

        if (timeout == null) {
            handleInitial(phone)
        } else {
            handleRequestedCode(phone, code ?: "", timeout)
        }
    }

    private fun FragmentAuthBinding.handleInitial(phone: String) {
        toggleInputsEnabledState(requestedCode = false)

        ivCloseButton.visible()
        ivBackButton.gone()
        tilPhone.visible()
        tilCode.gone()
        btnContinue.visible()
        btnLogin.gone()
        btnRequestCodeAgain.gone()
        tvCodeHint.gone()

        etPhone.setText(phone)
        etPhone.setSelection(phone.length)
    }

    private fun FragmentAuthBinding.handleRequestedCode(phone: String, code: String, timeout: Int) {
        toggleInputsEnabledState(requestedCode = true)

        ivCloseButton.gone()
        ivBackButton.visible()
        tilPhone.visible()
        tilCode.visible()
        btnContinue.gone()
        btnLogin.visible()

        etPhone.setText(phone)
        etPhone.setSelection(phone.length)
        etCode.setText(code)
        etCode.setSelection(code.length)

        handleTimeout(timeout)
    }

    private fun FragmentAuthBinding.toggleInputsEnabledState(requestedCode: Boolean) {
        if (requestedCode) {
            etPhone.disable()
            etCode.enable()
            btnContinue.disable()
            btnLogin.enable()
            btnRequestCodeAgain.enable()
        } else {
            etPhone.enable()
            etCode.disable()
            btnContinue.enable()
            btnLogin.disable()
            btnRequestCodeAgain.disable()
        }
    }

    private fun FragmentAuthBinding.handleTimeout(timeout: Int) {
        if (timeout > 0) {
            btnRequestCodeAgain.gone()
            tvCodeHint.visible()
            tvCodeHint.text = getString(R.string.code_hint, timeout)
        } else {
            btnRequestCodeAgain.visible()
            tvCodeHint.gone()
        }
    }
}