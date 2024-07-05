package com.streafy.pizzashift2024.navigation

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalRouterImpl @Inject constructor(
    private val router: Router
) : GlobalRouter {

    override fun open(screen: Screen) {
        router.navigateTo(screen)
    }

    override fun openAsRoot(screen: Screen) {
        router.newRootScreen(screen)
    }

    override fun pop() {
        router.exit()
    }
}