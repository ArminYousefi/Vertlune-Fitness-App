package com.github.arminyousefi.vertlune.domain.use_case.products

import com.github.arminyousefi.vertlune.domain.model.Product
import com.github.arminyousefi.vertlune.domain.repository.ProductRepository
import com.github.arminyousefi.vertlune.domain.util.Resource
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetProductsByCategoryUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(category: String): Flow<Resource<List<Product>>> {
        return repository.getProductsByCategory(category)
    }
}