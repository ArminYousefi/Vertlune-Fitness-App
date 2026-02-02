package com.github.arminyousefi.vertlune.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String,
    val title: String,
    val category: String,
    val price: Double,
    val description: String,
    @SerializedName("imageAssetPath")
    val imagePath: String,
    val rating: Float = 0.0f

)