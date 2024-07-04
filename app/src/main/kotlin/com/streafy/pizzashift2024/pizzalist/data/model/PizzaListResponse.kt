package com.streafy.pizzashift2024.pizzalist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PizzaListResponse(
    @SerialName("catalog") val pizzaList: List<PizzaModel>,
    val success: Boolean
)