package com.streafy.pizzashift2024.auth.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.streafy.pizzashift2024.R
import com.streafy.pizzashift2024.databinding.FragmentAuthBinding
import com.streafy.pizzashift2024.shared.textValue
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
        with (binding) {
            btnContinue.setOnClickListener {
                viewModel.requestOtpCode(etPhone.textValue)
            }
            btnLogin.setOnClickListener {
                viewModel.signIn(etPhone.textValue, etCode.textValue.toInt())
            }
            btnRequestCodeAgain.setOnClickListener {
                viewModel.requestOtpCode(etPhone.textValue)
            }
            ivCloseButton.setOnClickListener {
                viewModel.onCloseButtonClick()
            }
            ivBackButton.setOnClickListener {
                viewModel.onBackButtonClick()
            }
        }
    }

    private fun handleState(state: AuthUiState) {
        when (state) {
            AuthUiState.Initial -> showInitial()
            AuthUiState.Loading -> showLoading()
            is AuthUiState.Error -> showError(state.message)
            is AuthUiState.RequestedCode -> showRequestedCode(state.timeout)
        }
    }

    private fun showInitial() {
        with(binding) {
            cpiLoading.visibility = View.GONE
            tvError.visibility = View.GONE
            btnContinue.isEnabled = true

            etPhone.isEnabled = true
            tilCode.visibility = View.GONE

            btnContinue.visibility = View.VISIBLE
            btnLogin.visibility = View.GONE

            ivCloseButton.visibility = View.VISIBLE
            ivBackButton.visibility = View.GONE

            tvCodeHint.visibility = View.GONE
            btnRequestCodeAgain.visibility = View.GONE
        }
    }

    private fun showLoading() {
        with(binding) {
            cpiLoading.visibility = View.VISIBLE
            btnRequestCodeAgain.visibility = View.GONE
            tvCodeHint.visibility = View.GONE
            tvError.visibility = View.GONE

            btnContinue.isEnabled = false
            btnLogin.isEnabled = false
        }
    }

    private fun showError(message: String?) {
        with(binding) {
            cpiLoading.visibility = View.GONE
            tvError.visibility = View.VISIBLE
            tvError.text = message ?: getString(R.string.unknown_error)

            btnContinue.isEnabled = true
            btnLogin.isEnabled = true
        }
    }

    private fun showRequestedCode(timeout: Int) {
        with(binding) {
            cpiLoading.visibility = View.GONE
            tvError.visibility = View.GONE
            btnLogin.isEnabled = true

            etPhone.isEnabled = false
            tilCode.visibility = View.VISIBLE

            btnContinue.visibility = View.GONE
            btnLogin.visibility = View.VISIBLE

            ivCloseButton.visibility = View.GONE
            ivBackButton.visibility = View.VISIBLE

            if (timeout > 0) {
                btnRequestCodeAgain.visibility = View.GONE
                tvCodeHint.visibility = View.VISIBLE
                tvCodeHint.text = getString(R.string.code_hint, timeout)
            } else {
                btnRequestCodeAgain.visibility = View.VISIBLE
                tvCodeHint.visibility = View.GONE
            }
        }
    }

}