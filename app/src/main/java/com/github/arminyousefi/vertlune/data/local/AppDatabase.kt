package com.github.arminyousefi.vertlune.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.arminyousefi.vertlune.data.local.dao.DiscoveryDao
import com.github.arminyousefi.vertlune.data.local.dao.ProductDao
import com.github.arminyousefi.vertlune.data.local.entity.DiscoveryEntity
import com.github.arminyousefi.vertlune.data.local.entity.ProductEntity

@Database(
    entities = [DiscoveryEntity::class, ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun discoveryDao(): DiscoveryDao
    abstract fun productDao(): ProductDao
}