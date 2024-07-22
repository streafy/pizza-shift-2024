package com.streafy.pizzashift2024.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streafy.pizzashift2024.auth.domain.RequestOtpCodeUseCase
import com.streafy.pizzashift2024.auth.domain.SignInUseCase
import com.streafy.pizzashift2024.auth.navigation.AuthRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val requestOtpCodeUseCase: RequestOtpCodeUseCase,
    private val signInUseCase: SignInUseCase,
    private val router: AuthRouter
) : ViewModel() {

    private var _state = MutableStateFlow(
        AuthUiState(
            phone = "",
            code = null,
            timeout = null,
            isLoading = false,
            isError = false,
            errorMessage = null
        )
    )
    val state: StateFlow<AuthUiState> get() = _state

    private var timerJob: Job? = null

    fun requestOtpCode() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val phone = state.value.phone
                val timeout = requestOtpCodeUseCase(phone) / 1000
                _state.update { it.copy(timeout = timeout, isLoading = false, isError = false) }

                startTimer(durationSeconds = timeout)
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                onErrorOccurred(e.localizedMessage)
            }
        }
    }

    fun signIn() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val phone = state.value.phone
                val code = state.value.code?.toInt() ?: error("Incorrect code")

                signInUseCase(phone, code)
                router.goBack()
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                onErrorOccurred(e.localizedMessage)
            }
        }
    }

    fun onCloseButtonClick() {
        timerJob?.cancel()
        router.goBack()
    }

    fun onBackButtonClick() {
        timerJob?.cancel()
        _state.update {
            it.copy(
                code = null,
                timeout = null
            )
        }
    }

    fun dismissError() {
        _state.update {
            it.copy(isLoading = false, isError = false, errorMessage = null)
        }
    }

    fun onPhoneChanged(phone: String) {
        _state.update {
            it.copy(phone = phone)
        }
    }

    fun onCodeChanged(code: String) {
        _state.update {
            it.copy(code = code)
        }
    }

    private fun startTimer(durationSeconds: Int) {
        timerJob = viewModelScope.launch {
            for (secondsUntilFinished in durationSeconds downTo 0) {
                onTimerTick(secondsUntilFinished)
                delay(1000)
            }
        }
    }

    private fun onTimerTick(secondsUntilFinished: Int) {
        _state.update {
            it.copy(timeout = secondsUntilFinished)
        }
    }

    private fun onErrorOccurred(message: String?) {
        _state.update {
            it.copy(isLoading = false, isError = true, errorMessage = message)
        }
    }
}

data class AuthUiState(
    val phone: String,
    val code: String?,
    val timeout: Int?,
    val isLoading: Boolean,
    val isError: Boolean,
    val errorMessage: String?
)