package com.streafy.pizzashift2024.pizzacard.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.streafy.pizzashift2024.shared.pizza.domain.Pizza
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PizzaCardViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(
        PizzaCardUiState(
            pizza = null,
            selectedSizeIndex = 0,
            selectedToppingIndexes = emptySet()
        )
    )
    val state: StateFlow<PizzaCardUiState> get() = _state

    fun setPizzaData(pizza: Pizza) {
        _state.value = PizzaCardUiState(
            pizza = pizza,
            selectedSizeIndex = 0,
            selectedToppingIndexes = emptySet()
        )
    }

    fun onSizeSelected(index: Int) {
        _state.value = _state.value.copy(selectedSizeIndex = index)
    }

    fun onToppingSelected(index: Int) {
        val currentToppings = _state.value.selectedToppingIndexes
        Log.d("PizzaCardViewModel", "onToppingSelected: $currentToppings")
        val newToppings = if (currentToppings.contains(index)) {
            currentToppings - index
        } else {
            currentToppings + index
        }
        _state.value = _state.value.copy(
            selectedToppingIndexes = newToppings
        )
    }
}


data class PizzaCardUiState(
    val pizza: Pizza?,
    val selectedSizeIndex: Int,
    val selectedToppingIndexes: Set<Int>
)