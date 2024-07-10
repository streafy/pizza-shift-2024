package com.streafy.pizzashift2024.pizzalist.navigation

import com.streafy.pizzashift2024.shared.pizza.domain.Pizza

interface PizzaListRouter {

    fun openPizzaCard(pizza: Pizza)
}