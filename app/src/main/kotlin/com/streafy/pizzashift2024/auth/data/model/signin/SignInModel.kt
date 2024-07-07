package com.streafy.pizzashift2024.auth.data.model.signin

import kotlinx.serialization.Serializable

@Serializable
data class SignInModel(
    val phone: String,
    val code: Int
)
