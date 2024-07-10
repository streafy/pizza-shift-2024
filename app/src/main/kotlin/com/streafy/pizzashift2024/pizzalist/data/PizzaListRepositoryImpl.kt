package com.streafy.pizzashift2024.pizzalist.data

import com.streafy.pizzashift2024.pizzalist.data.converter.PizzaModelConverter
import com.streafy.pizzashift2024.pizzalist.domain.PizzaListRepository
import com.streafy.pizzashift2024.shared.pizza.domain.Pizza
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PizzaListRepositoryImpl @Inject constructor(
    private val api: PizzaListApi,
    private val converter: PizzaModelConverter
) : PizzaListRepository {

    override suspend fun getPizzaList(): List<Pizza> =
        api.getPizzaList().pizzaList.map { converter.convert(it) }
}