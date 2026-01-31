package com.github.arminyousefi.vertlune.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.arminyousefi.vertlune.data.local.entity.DiscoveryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DiscoveryDao {
    @Query("SELECT * FROM discovery_items")
    fun getAllDiscoveryItems(): Flow<List<DiscoveryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<DiscoveryEntity>)

    @Query("SELECT COUNT(*) FROM discovery_items")
    suspend fun getCount(): Int
}