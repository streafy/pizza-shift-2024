package com.streafy.pizzashift2024.pizzalist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PizzaModel(
    val id: String,
    val name: String,
    val ingredients: List<Ingredient>,
    val toppings: List<Topping>,
    val description: String,
    val sizes: List<Size>,
    val doughs: List<Dough>,
    val calories: Int,
    val protein: String,
    val totalFat: String,
    val carbohydrates: String,
    val sodium: String,
    val allergens: List<String>,
    val isVegetarian: Boolean,
    val isGlutenFree: Boolean,
    val isNew: Boolean,
    val isHit: Boolean,
    @SerialName("img") val imageUri: String,
)