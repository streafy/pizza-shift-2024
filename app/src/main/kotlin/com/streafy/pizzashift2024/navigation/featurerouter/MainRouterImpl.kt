package com.streafy.pizzashift2024.navigation.featurerouter

import com.streafy.pizzashift2024.main.MainRouter
import com.streafy.pizzashift2024.navigation.GlobalRouter
import com.streafy.pizzashift2024.orders.navigation.ordersScreen
import com.streafy.pizzashift2024.pizzalist.navigation.pizzaListScreen
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRouterImpl @Inject constructor(
    private val router: GlobalRouter
) : MainRouter {

    override fun openPizzaList() {
        router.openAsRoot(pizzaListScreen)
    }

    override fun openOrders() {
        router.openAsRoot(ordersScreen)
    }

    override fun openCart() {
        TODO("Not yet implemented")
    }

    override fun openProfile() {
        TODO("Not yet implemented")
    }
}