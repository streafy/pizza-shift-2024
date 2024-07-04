package com.streafy.pizzashift2024.pizzalist.data

import com.streafy.pizzashift2024.pizzalist.data.model.PizzaModel
import com.streafy.pizzashift2024.pizzalist.domain.Pizza

class PizzaModelConverter {

    fun convert(model: PizzaModel): Pizza =
        Pizza(
            id = model.id.toInt(),
            name = model.name,
            description = model.description,
            price = model.sizes.first().price,
            imageUri = model.imageUri,
        )
}