package com.streafy.pizzashift2024.pizzalist.domain

data class Pizza(
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val imageUri: String,
)
