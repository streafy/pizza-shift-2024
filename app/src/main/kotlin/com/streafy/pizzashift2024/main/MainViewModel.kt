package com.streafy.pizzashift2024.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val router: MainRouter
) : ViewModel() {
    private val _state = MutableStateFlow(
        MainState(
            NavigationOption.entries,
            NavigationOption.PIZZA_LIST
        )
    )
    val state: StateFlow<MainState> get() = _state

    fun openOption(option: NavigationOption) {
        _state.value = _state.value.copy(selectedOption = option)
        when (option) {
            NavigationOption.PIZZA_LIST -> router.openPizzaList()
            NavigationOption.ORDERS -> router.openOrders()
            NavigationOption.CART -> router.openCart()
            NavigationOption.PROFILE -> router.openProfile()
        }
    }

}

data class MainState(
    val navigationOptions: List<NavigationOption>,
    val selectedOption: NavigationOption,
)

enum class NavigationOption {
    PIZZA_LIST, ORDERS, CART, PROFILE
}
