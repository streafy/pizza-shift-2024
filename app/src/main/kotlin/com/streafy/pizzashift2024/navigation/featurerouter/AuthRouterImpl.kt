package com.streafy.pizzashift2024.navigation.featurerouter

import com.streafy.pizzashift2024.auth.navigation.AuthRouter
import com.streafy.pizzashift2024.navigation.GlobalRouter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRouterImpl @Inject constructor(
    private val router: GlobalRouter
) : AuthRouter {

    override fun goBack() {
        router.pop()
    }
}