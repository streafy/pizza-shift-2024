package com.streafy.pizzashift2024.auth.presentation

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streafy.pizzashift2024.auth.domain.RequestOtpCodeUseCase
import com.streafy.pizzashift2024.auth.domain.SignInUseCase
import com.streafy.pizzashift2024.auth.navigation.AuthRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val requestOtpCodeUseCase: RequestOtpCodeUseCase,
    private val signInUseCase: SignInUseCase,
    private val router: AuthRouter
) : ViewModel() {

    private var _state = MutableStateFlow<AuthUiState>(AuthUiState.Initial)
    val state: StateFlow<AuthUiState> get() = _state

    private var timer: CountDownTimer? = null

    fun requestOtpCode(phone: String) {
        viewModelScope.launch {
            _state.value = AuthUiState.Loading
            try {
                val timeout = requestOtpCodeUseCase(phone)
                _state.value = AuthUiState.RequestedCode(timeout)

                startTimer(timeout.toLong())
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                _state.value = AuthUiState.Error(e.message)
            }
        }
    }

    fun signIn(phone: String, code: Int) {
        viewModelScope.launch {
            _state.value = AuthUiState.Loading
            try {
                signInUseCase(phone, code)
                router.goBack()
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                stopTimer()
                _state.value = AuthUiState.Error(e.message)
            }
        }
    }

    fun onCloseButtonClick() {
        stopTimer()
        router.goBack()
    }

    fun onBackButtonClick() {
        stopTimer()
        _state.value = AuthUiState.Initial
    }

    private fun startTimer(durationMillis: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(durationMillis, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                _state.value = AuthUiState.RequestedCode((millisUntilFinished / 1000).toInt())
            }

            override fun onFinish() {
                _state.value = AuthUiState.RequestedCode(0)
            }
        }.start()
    }

    private fun stopTimer() {
        timer?.cancel()
        timer = null
    }

    override fun onCleared() {
        super.onCleared()
        timer = null
    }
}

sealed interface AuthUiState {

    data object Initial : AuthUiState
    data object Loading : AuthUiState
    data class RequestedCode(val timeout: Int) : AuthUiState
    data class Error(val message: String?) : AuthUiState
}