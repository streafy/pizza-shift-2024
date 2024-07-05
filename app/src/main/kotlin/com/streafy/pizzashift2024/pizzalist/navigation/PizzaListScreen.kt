package com.streafy.pizzashift2024.pizzalist.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.streafy.pizzashift2024.pizzalist.presentation.PizzaListFragment

val pizzaListScreen = FragmentScreen {
    PizzaListFragment.newInstance()
}