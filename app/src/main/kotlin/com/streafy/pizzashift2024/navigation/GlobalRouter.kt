package com.streafy.pizzashift2024.navigation

import com.github.terrakok.cicerone.Screen

interface GlobalRouter {

    fun open(screen: Screen)

    fun openAsRoot(screen: Screen)

    fun pop()
}