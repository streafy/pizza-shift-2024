package com.streafy.pizzashift2024.pizzalist.data.converter

import com.streafy.pizzashift2024.pizzalist.data.model.ToppingModel
import com.streafy.pizzashift2024.shared.network.NetworkUrlConfig
import com.streafy.pizzashift2024.shared.pizza.domain.Topping
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToppingModelConverter @Inject constructor() {

    fun convert(
        id: Int,
        model: ToppingModel
    ): Topping = with(model) {
        Topping(
            id = id,
            name = name,
            price = cost,
            imageUri = "${NetworkUrlConfig.IMAGE_BASE_URL}${model.imageUri}"
        )
    }
}