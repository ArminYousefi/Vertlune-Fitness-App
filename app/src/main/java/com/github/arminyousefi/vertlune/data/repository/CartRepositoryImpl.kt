package com.github.arminyousefi.vertlune.data.repository

import com.github.arminyousefi.vertlune.data.local.dao.CartDao
import com.github.arminyousefi.vertlune.data.mapper.toDomain
import com.github.arminyousefi.vertlune.data.mapper.toEntity
import com.github.arminyousefi.vertlune.domain.model.CartItem
import com.github.arminyousefi.vertlune.domain.repository.CartRepository
import com.github.arminyousefi.vertlune.domain.util.Resource
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

@Singleton
class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao
) : CartRepository {

    override fun getCartItems(): Flow<Resource<List<CartItem>>> =
        cartDao.getAllCartItems()
            .map { entities -> Resource.Success(entities.map { it.toDomain() }) as Resource<List<CartItem>> }
            .onStart { emit(Resource.Loading()) }
            .catch { e ->
                emit(Resource.Error(
                    uiMessage = "Failed to fetch cart items",
                    devMessage = "getCartItems DB failure: ${e.localizedMessage}"
                ))
            }

    override suspend fun addToCart(item: CartItem): Resource<Unit> =
        try {
            cartDao.insertCartItem(item.toEntity())
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(
                uiMessage = "Failed to add to cart",
                devMessage = "addToCart DB failure: ${e.localizedMessage}"
            )
        }

    override suspend fun removeFromCart(productId: String): Resource<Unit> =
        try {
            cartDao.removeCartItem(productId)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(
                uiMessage = "Failed to remove from cart",
                devMessage = "removeFromCart DB failure: ${e.localizedMessage}"
            )
        }

    override suspend fun updateQuantity(productId: String, quantity: Int): Resource<Unit> =
        try {
            val currentItem = cartDao.getAllCartItems().first().find { it.productId == productId }
                ?: return Resource.Error(
                    uiMessage = "Item not found",
                    devMessage = "updateQuantity DB failure: productId $productId not found"
                )
            cartDao.updateCartItem(currentItem.copy(quantity = quantity))
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(
                uiMessage = "Failed to update quantity",
                devMessage = "updateQuantity DB failure: ${e.localizedMessage}"
            )
        }

    override suspend fun clearCart(): Resource<Unit> =
        try {
            cartDao.clearCart()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(
                uiMessage = "Failed to clear cart",
                devMessage = "clearCart DB failure: ${e.localizedMessage}"
            )
        }
}
