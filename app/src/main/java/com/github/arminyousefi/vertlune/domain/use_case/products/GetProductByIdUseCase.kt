package com.github.arminyousefi.vertlune.domain.use_case.products

import com.github.arminyousefi.vertlune.domain.model.Product
import com.github.arminyousefi.vertlune.domain.repository.ProductRepository
import com.github.arminyousefi.vertlune.domain.util.Resource
import jakarta.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: String): Resource<Product> {
        return repository.getProductById(id)
    }
}