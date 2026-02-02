package com.github.arminyousefi.vertlune.domain.use_case

import com.github.arminyousefi.vertlune.domain.use_case.cart.AddToCartUseCase
import com.github.arminyousefi.vertlune.domain.use_case.cart.ClearCartUseCase
import com.github.arminyousefi.vertlune.domain.use_case.cart.GetCartItemsUseCase
import com.github.arminyousefi.vertlune.domain.use_case.cart.RemoveFromCartUseCase
import com.github.arminyousefi.vertlune.domain.use_case.cart.UpdateQuantityUseCase
import javax.inject.Inject

data class CartUseCases @Inject constructor(
    val getCartItems: GetCartItemsUseCase,
    val addToCart: AddToCartUseCase,
    val removeFromCart: RemoveFromCartUseCase,
    val updateQuantity: UpdateQuantityUseCase,
    val clearCart: ClearCartUseCase
)