package com.streafy.pizzashift2024.pizzalist.data.converter

import com.streafy.pizzashift2024.pizzalist.data.model.PizzaModel
import com.streafy.pizzashift2024.shared.network.NetworkUrlConfig
import com.streafy.pizzashift2024.shared.pizza.domain.Pizza
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PizzaModelConverter @Inject constructor(
    private val toppingConverter: ToppingModelConverter,
    private val sizeModelConverter: SizeModelConverter
) {

    fun convert(model: PizzaModel): Pizza = with(model) {
        Pizza(
            id = id,
            name = name,
            toppings = toppings.mapIndexed { index, topping ->
                toppingConverter.convert(
                    index,
                    topping
                )
            },
            description = description,
            sizes = sizes.map { sizeModelConverter.convert(it) },
            imageUri = "${NetworkUrlConfig.IMAGE_BASE_URL}${model.imageUri}"
        )
    }
}