package com.streafy.pizzashift2024.shared.pizza.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pizza(
    val id: String,
    val name: String,
    val toppings: List<Topping>,
    val description: String,
    val sizes: List<Size>,
    val imageUri: String,
) : Parcelable
