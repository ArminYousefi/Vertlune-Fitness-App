package com.github.arminyousefi.vertlune.domain.model

data class Product(
    val id: String,
    val title: String,
    val category: String,
    val price: Double,
    val description: String,
    val imagePath: String,
    val rating: Float
)