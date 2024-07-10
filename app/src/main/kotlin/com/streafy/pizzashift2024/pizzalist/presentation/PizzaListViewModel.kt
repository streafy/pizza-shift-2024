package com.streafy.pizzashift2024.pizzalist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streafy.pizzashift2024.pizzalist.domain.GetPizzaListUseCase
import com.streafy.pizzashift2024.shared.pizza.domain.Pizza
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PizzaListViewModel @Inject constructor(
    private val getPizzaListUseCase: GetPizzaListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<PizzaListUiState>(PizzaListUiState.Initial)
    val state: StateFlow<PizzaListUiState> get() = _state

    fun loadData() {
        viewModelScope.launch {
            _state.value = PizzaListUiState.Loading

            try {
                val pizzas = getPizzaListUseCase()
                _state.value = PizzaListUiState.Content(pizzas)
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                _state.value = PizzaListUiState.Error(e.message)
            }
        }
    }
}

sealed interface PizzaListUiState {

    data object Initial : PizzaListUiState
    data object Loading : PizzaListUiState
    data class Content(val pizzas: List<Pizza>) : PizzaListUiState
    data class Error(val message: String?) : PizzaListUiState
}