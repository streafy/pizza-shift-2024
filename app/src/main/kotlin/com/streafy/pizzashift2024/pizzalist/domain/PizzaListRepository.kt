package com.streafy.pizzashift2024.pizzalist.domain

interface PizzaListRepository {

    suspend fun getPizzaList(): List<Pizza>
}