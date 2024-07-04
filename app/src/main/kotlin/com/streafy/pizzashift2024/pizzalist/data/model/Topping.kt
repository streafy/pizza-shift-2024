package com.streafy.pizzashift2024.pizzalist.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Topping(
    val cost: Int,
    val img: String,
    val name: String
)