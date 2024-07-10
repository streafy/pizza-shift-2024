package com.streafy.pizzashift2024.pizzacard.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.streafy.pizzashift2024.pizzacard.presentation.PizzaCardFragment
import com.streafy.pizzashift2024.shared.pizza.domain.Pizza

fun pizzaCardScreen(pizza: Pizza) = FragmentScreen {
    PizzaCardFragment.newInstance(pizza)
}