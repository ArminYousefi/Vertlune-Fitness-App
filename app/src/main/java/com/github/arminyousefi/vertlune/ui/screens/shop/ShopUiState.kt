package com.github.arminyousefi.vertlune.ui.screens.shop

import com.github.arminyousefi.vertlune.domain.model.Product

data class ShopUiState(
    val products: List<Product> = emptyList(),
    val originalProducts: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val currentSort: ProductSort = ProductSort.NONE,
    val currentFilter: ProductFilter = ProductFilter.ALL
)

enum class ProductSort {
    NONE,
    PRICE_LOW_TO_HIGH,
    PRICE_HIGH_TO_LOW,
    RATING_HIGH_TO_LOW
}

enum class ProductFilter {
    ALL,
    SHIRT,
    PANTS,
    TOP,
    SHORTS,
    SWEATER
}