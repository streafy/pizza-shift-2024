package com.streafy.pizzashift2024.pizzalist.domain

import com.streafy.pizzashift2024.shared.pizza.domain.Pizza

interface PizzaListRepository {

    suspend fun getPizzaList(): List<Pizza>
}