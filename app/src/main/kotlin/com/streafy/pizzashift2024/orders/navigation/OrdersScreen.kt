package com.streafy.pizzashift2024.orders.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.streafy.pizzashift2024.orders.presentation.OrdersFragment

val ordersScreen = FragmentScreen {
    OrdersFragment.newInstance()
}