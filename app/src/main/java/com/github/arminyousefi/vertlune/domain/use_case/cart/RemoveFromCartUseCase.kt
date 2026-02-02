package com.github.arminyousefi.vertlune.domain.use_case.cart

import com.github.arminyousefi.vertlune.domain.repository.CartRepository
import com.github.arminyousefi.vertlune.domain.util.Resource
import javax.inject.Inject

class RemoveFromCartUseCase @Inject constructor(private val repository: CartRepository) {
    suspend operator fun invoke(productId: String): Resource<Unit> = repository.removeFromCart(productId)
}