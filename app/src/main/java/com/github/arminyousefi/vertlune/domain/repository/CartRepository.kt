package com.github.arminyousefi.vertlune.domain.repository

import com.github.arminyousefi.vertlune.domain.model.CartItem
import com.github.arminyousefi.vertlune.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCartItems(): Flow<Resource<List<CartItem>>>

    suspend fun addToCart(item: CartItem): Resource<Unit>

    suspend fun removeFromCart(productId: String): Resource<Unit>

    suspend fun updateQuantity(productId: String, quantity: Int): Resource<Unit>

    suspend fun clearCart(): Resource<Unit>
}