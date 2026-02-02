package com.github.arminyousefi.vertlune.ui.screens.home

import com.github.arminyousefi.vertlune.domain.model.Product

data class HomeUiState(
    val isLoading: Boolean = false,
    val bestSellers: List<Product> = emptyList(),
    val errorMessage: String? = null
)