package com.streafy.pizzashift2024.navigation.featurerouter

import com.streafy.pizzashift2024.navigation.GlobalRouter
import com.streafy.pizzashift2024.pizzacard.navigation.pizzaCardScreen
import com.streafy.pizzashift2024.pizzalist.navigation.PizzaListRouter
import com.streafy.pizzashift2024.shared.pizza.domain.Pizza
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PizzaListRouterImpl @Inject constructor(
    private val router: GlobalRouter
) : PizzaListRouter {

    override fun openPizzaCard(pizza: Pizza) {
        router.open(pizzaCardScreen(pizza))
    }
}