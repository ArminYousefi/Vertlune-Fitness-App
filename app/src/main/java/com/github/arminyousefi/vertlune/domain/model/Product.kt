package com.github.arminyousefi.vertlune.domain.model

data class Product(
    val id: String,          // شناسه‌ای مثل "LULU-001"
    val title: String,       // Surge Short
    val category: String,    // Shirt
    val price: Double,
    val description: String,
    val imagePath: String,   // مسیر عکس در assets
    val rating: Float
)