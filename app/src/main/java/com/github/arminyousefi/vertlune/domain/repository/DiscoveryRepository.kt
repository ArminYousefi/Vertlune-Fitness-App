package com.github.arminyousefi.vertlune.domain.repository

import com.github.arminyousefi.vertlune.domain.model.DiscoveryItem
import com.github.arminyousefi.vertlune.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface DiscoveryRepository {
    fun getDiscoveryItems(): Flow<Resource<List<DiscoveryItem>>>
    suspend fun addDiscoveryItem(item: DiscoveryItem): Resource<Unit>
}