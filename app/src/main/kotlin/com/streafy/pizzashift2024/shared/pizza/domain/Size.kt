package com.streafy.pizzashift2024.shared.pizza.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Size(
    val name: String,
    val price: Int,
) : Parcelable
