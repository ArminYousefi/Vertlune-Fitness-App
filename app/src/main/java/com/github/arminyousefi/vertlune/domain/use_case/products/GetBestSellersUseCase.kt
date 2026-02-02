package com.github.arminyousefi.vertlune.domain.use_case.products

import com.github.arminyousefi.vertlune.domain.model.Product
import com.github.arminyousefi.vertlune.domain.repository.ProductRepository
import com.github.arminyousefi.vertlune.domain.util.Resource
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetBestSellersUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<Resource<List<Product>>> {
        return repository.getBestSellers(minRating = 3f)
    }
}