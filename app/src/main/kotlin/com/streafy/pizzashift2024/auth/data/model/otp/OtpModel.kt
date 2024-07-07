package com.streafy.pizzashift2024.auth.data.model.otp

import kotlinx.serialization.Serializable

@Serializable
data class OtpModel(
    val phone: String
)
