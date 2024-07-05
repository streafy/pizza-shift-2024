package com.streafy.pizzashift2024.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val router: MainRouter
) : ViewModel() {

    fun openOption(option: NavigationOption) {
        when (option) {
            NavigationOption.PIZZA_LIST -> router.openPizzaList()
            NavigationOption.ORDERS -> router.openOrders()
            NavigationOption.CART -> router.openCart()
            NavigationOption.PROFILE -> router.openProfile()
        }
    }

}

enum class NavigationOption {
    PIZZA_LIST, ORDERS, CART, PROFILE
}
