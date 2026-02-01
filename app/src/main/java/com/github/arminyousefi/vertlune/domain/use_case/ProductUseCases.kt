package com.github.arminyousefi.vertlune.domain.use_case

import com.github.arminyousefi.vertlune.domain.use_case.products.GetAllProductsUseCase
import com.github.arminyousefi.vertlune.domain.use_case.products.GetProductByIdUseCase
import com.github.arminyousefi.vertlune.domain.use_case.products.GetProductsByCategoryUseCase

data class ProductUseCases(
    val getAllProducts: GetAllProductsUseCase,
    val getProductsByCategory: GetProductsByCategoryUseCase,
    val getProductById: GetProductByIdUseCase
)