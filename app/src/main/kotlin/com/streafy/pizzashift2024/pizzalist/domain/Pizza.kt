package com.streafy.pizzashift2024.pizzalist.domain

data class Pizza(
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val imageUri: String,
)

fun createMockData() = List(40) { index ->
    Pizza(
        id = index,
        name = "Пепперони",
        description = "Классическая пицца с пепперони.",
        price = 499,
        imageUri = "https://shift-backend.onrender.com/static/images/pizza/5.jpeg"
    )
}
