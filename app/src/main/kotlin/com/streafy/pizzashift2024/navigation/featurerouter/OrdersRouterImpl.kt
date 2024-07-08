package com.streafy.pizzashift2024.navigation.featurerouter

import com.streafy.pizzashift2024.auth.navigation.authScreen
import com.streafy.pizzashift2024.navigation.GlobalRouter
import com.streafy.pizzashift2024.orders.navigation.OrdersRouter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrdersRouterImpl @Inject constructor(
    private val router: GlobalRouter
) : OrdersRouter {

    override fun openAuth() {
        router.open(authScreen)
    }

    override fun openDetails() {
        TODO("Not yet implemented")
    }
}