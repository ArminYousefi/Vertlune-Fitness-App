package com.github.arminyousefi.vertlune.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "discovery_items")
data class DiscoveryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mainTitle: String,
    val subTag: String,
    val imageAssetPath: String?, // برای موارد پیش‌فرض
    val imageBase64: String?,    // برای تصاویر کاربر
    val isUserCreated: Boolean
)