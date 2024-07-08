package com.streafy.pizzashift2024.pizzalist.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SizeModel(
    val name: String,
    val price: Int
)