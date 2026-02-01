package com.github.arminyousefi.vertlune.domain.use_case

import com.github.arminyousefi.vertlune.domain.use_case.discovery.AddDiscoveryItemUseCase
import com.github.arminyousefi.vertlune.domain.use_case.discovery.GetDiscoveryItemsUseCase

data class DiscoveryUseCases(
    val getDiscoveryItems: GetDiscoveryItemsUseCase,
    val addDiscoveryItem: AddDiscoveryItemUseCase
)