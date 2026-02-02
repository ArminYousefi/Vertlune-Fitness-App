package com.github.arminyousefi.vertlune.domain.model

data class CartItem(
    val productId: String,
    val title: String,
    val price: Double,
    val imagePath: String,
    val quantity: Int
)