package com.streafy.pizzashift2024.orders.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streafy.pizzashift2024.orders.navigation.OrdersRouter
import com.streafy.pizzashift2024.shared.tokenstorage.CheckIsAuthUseCase
import com.streafy.pizzashift2024.shared.tokenstorage.ClearTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val isAuthUseCase: CheckIsAuthUseCase,
    private val clearTokenUseCase: ClearTokenUseCase,
    private val router: OrdersRouter,
) : ViewModel() {

    private val _state = MutableStateFlow<OrdersUiState>(OrdersUiState.Initial)
    val state: StateFlow<OrdersUiState> get() = _state

    fun checkAuth() {
        viewModelScope.launch {
            try {
                val isAuthenticated = isAuthUseCase()

                if (isAuthenticated) {
                    _state.value = OrdersUiState.Authenticated
                } else {
                    delay(1000)
                    router.openAuth()
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun clearToken() {
        viewModelScope.launch {
            try {
                clearTokenUseCase()
                _state.value = OrdersUiState.Initial
                router.openAuth()
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                throw e
            }
        }
    }
}

sealed interface OrdersUiState {

    data object Initial : OrdersUiState
    data object Authenticated : OrdersUiState
}