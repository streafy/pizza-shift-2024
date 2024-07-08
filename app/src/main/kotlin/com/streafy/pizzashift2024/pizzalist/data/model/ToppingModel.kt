package com.streafy.pizzashift2024.pizzalist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ToppingModel(
    val cost: Int,
    @SerialName("img") val imageUri: String,
    val name: String
)