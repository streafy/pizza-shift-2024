package com.streafy.pizzashift2024.auth.data.model.signin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    @SerialName("_id") val id: String,
    val phone: String,
    @SerialName("firstname") val firstName: String,
    @SerialName("middlename") val middleName: String,
    @SerialName("lastname") val lastName: String,
    val email: String,
    val city: String,
)
