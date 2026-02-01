package com.github.arminyousefi.vertlune.domain.use_case.discovery

import com.github.arminyousefi.vertlune.domain.model.DiscoveryItem
import com.github.arminyousefi.vertlune.domain.repository.DiscoveryRepository
import com.github.arminyousefi.vertlune.domain.util.Resource
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetDiscoveryItemsUseCase @Inject constructor(
    private val repository: DiscoveryRepository
) {
    operator fun invoke(): Flow<Resource<List<DiscoveryItem>>> {
        return repository.getDiscoveryItems()
    }
}