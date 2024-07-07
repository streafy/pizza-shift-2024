package com.streafy.pizzashift2024.auth.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.streafy.pizzashift2024.auth.presentation.AuthFragment

val authScreen = FragmentScreen {
    AuthFragment.newInstance()
}