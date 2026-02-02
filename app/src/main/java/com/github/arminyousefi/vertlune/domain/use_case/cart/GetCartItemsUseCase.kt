package com.github.arminyousefi.vertlune.domain.use_case.cart

import com.github.arminyousefi.vertlune.domain.model.CartItem
import com.github.arminyousefi.vertlune.domain.repository.CartRepository
import com.github.arminyousefi.vertlune.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartItemsUseCase @Inject constructor(private val repository: CartRepository) {
    operator fun invoke(): Flow<Resource<List<CartItem>>> = repository.getCartItems()
}