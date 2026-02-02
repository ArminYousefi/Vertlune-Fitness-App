package com.github.arminyousefi.vertlune.ui.screens.discover

import com.github.arminyousefi.vertlune.domain.model.DiscoveryItem

data class DiscoverUiState(
    val isLoading: Boolean = false,
    val items: List<DiscoveryItem> = emptyList(),
    val errorMessage: String? = null
)