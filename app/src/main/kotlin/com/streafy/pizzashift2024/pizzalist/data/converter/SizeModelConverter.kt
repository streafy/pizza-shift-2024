package com.streafy.pizzashift2024.pizzalist.data.converter

import com.streafy.pizzashift2024.pizzalist.data.model.SizeModel
import com.streafy.pizzashift2024.shared.pizza.domain.Size
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SizeModelConverter @Inject constructor() {

    fun convert(model: SizeModel): Size = with(model) {
        Size(
            name = name,
            price = price
        )
    }
}