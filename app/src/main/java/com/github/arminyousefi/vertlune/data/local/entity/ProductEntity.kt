package com.github.arminyousefi.vertlune.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String,
    val title: String,
    val category: String,
    val price: Double,
    val description: String,
    val imagePath: String,
    val rating: Float
)