package com.github.arminyousefi.vertlune.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey val productId: String,
    val title: String,
    val price: Double,
    val imagePath: String,
    val quantity: Int = 1
)