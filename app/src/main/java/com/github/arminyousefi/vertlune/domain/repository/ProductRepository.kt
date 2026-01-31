package com.github.arminyousefi.vertlune.domain.repository

import com.github.arminyousefi.vertlune.domain.model.Product
import com.github.arminyousefi.vertlune.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProducts(): Flow<Resource<List<Product>>>
    fun getProductsByCategory(category: String): Flow<Resource<List<Product>>>
    suspend fun getProductById(id: String): Resource<Product>
}