package com.github.arminyousefi.vertlune.domain.use_case.discovery

import com.github.arminyousefi.vertlune.domain.model.DiscoveryItem
import com.github.arminyousefi.vertlune.domain.repository.DiscoveryRepository
import com.github.arminyousefi.vertlune.domain.util.Resource
import jakarta.inject.Inject

class AddDiscoveryItemUseCase @Inject constructor(
    private val repository: DiscoveryRepository
) {
    suspend operator fun invoke(item: DiscoveryItem): Resource<Unit> {
        return repository.addDiscoveryItem(item)
    }
}
