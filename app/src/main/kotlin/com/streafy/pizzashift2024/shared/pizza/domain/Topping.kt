package com.streafy.pizzashift2024.shared.pizza.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Topping(
    val id: Int,
    val name: String,
    val price: Int,
    val imageUri: String,
) : Parcelable
