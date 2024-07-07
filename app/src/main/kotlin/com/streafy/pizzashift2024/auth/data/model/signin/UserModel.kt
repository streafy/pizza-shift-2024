package com.streafy.pizzashift2024.auth.data.model.signin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    @SerialName("_id") val id: String,
    val phone: String,
    @SerialName("firstname") val firstName: String? = null,
    @SerialName("middlename") val middleName: String? = null,
    @SerialName("lastname") val lastName: String? = null,
    val email: String? = null,
    val city: String? = null,
)
