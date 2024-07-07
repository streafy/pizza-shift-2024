package com.streafy.pizzashift2024.pizzalist.data

import com.streafy.pizzashift2024.pizzalist.data.model.PizzaModel
import com.streafy.pizzashift2024.pizzalist.domain.Pizza
import com.streafy.pizzashift2024.shared.network.NetworkUrlConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PizzaModelConverter @Inject constructor() {

    fun convert(model: PizzaModel): Pizza =
        Pizza(
            id = model.id.toInt(),
            name = model.name,
            description = model.description,
            price = model.sizes.first().price,
            imageUri = "${NetworkUrlConfig.IMAGE_BASE_URL}${model.imageUri}",
        )
}