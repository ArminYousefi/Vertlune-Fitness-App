package com.github.arminyousefi.vertlune.domain.util.mapper

import com.github.arminyousefi.vertlune.domain.model.CartItem
import com.github.arminyousefi.vertlune.domain.model.Product

fun Product.toCartItem(quantity: Int = 1): CartItem {
    return CartItem(
        productId = this.id,
        title = this.title,
        price = this.price,
        imagePath = this.imagePath,
        quantity = quantity
    )
}