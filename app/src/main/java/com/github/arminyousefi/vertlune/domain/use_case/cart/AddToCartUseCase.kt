package com.github.arminyousefi.vertlune.domain.use_case.cart

import com.github.arminyousefi.vertlune.domain.model.CartItem
import com.github.arminyousefi.vertlune.domain.repository.CartRepository
import com.github.arminyousefi.vertlune.domain.util.Resource
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(private val repository: CartRepository) {
    suspend operator fun invoke(item: CartItem): Resource<Unit> = repository.addToCart(item)
}