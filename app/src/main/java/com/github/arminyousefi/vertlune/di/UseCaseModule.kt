package com.github.arminyousefi.vertlune.di

import com.github.arminyousefi.vertlune.domain.repository.CartRepository
import com.github.arminyousefi.vertlune.domain.repository.DiscoveryRepository
import com.github.arminyousefi.vertlune.domain.repository.ProductRepository
import com.github.arminyousefi.vertlune.domain.use_case.CartUseCases
import com.github.arminyousefi.vertlune.domain.use_case.DiscoveryUseCases
import com.github.arminyousefi.vertlune.domain.use_case.ProductUseCases
import com.github.arminyousefi.vertlune.domain.use_case.cart.AddToCartUseCase
import com.github.arminyousefi.vertlune.domain.use_case.cart.ClearCartUseCase
import com.github.arminyousefi.vertlune.domain.use_case.cart.GetCartItemsUseCase
import com.github.arminyousefi.vertlune.domain.use_case.cart.RemoveFromCartUseCase
import com.github.arminyousefi.vertlune.domain.use_case.cart.UpdateQuantityUseCase
import com.github.arminyousefi.vertlune.domain.use_case.discovery.AddDiscoveryItemUseCase
import com.github.arminyousefi.vertlune.domain.use_case.discovery.GetDiscoveryItemsUseCase
import com.github.arminyousefi.vertlune.domain.use_case.products.GetAllProductsUseCase
import com.github.arminyousefi.vertlune.domain.use_case.products.GetProductByIdUseCase
import com.github.arminyousefi.vertlune.domain.use_case.products.GetProductsByCategoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideProductUseCases(repository: ProductRepository): ProductUseCases {
        return ProductUseCases(
            getAllProducts = GetAllProductsUseCase(repository),
            getProductsByCategory = GetProductsByCategoryUseCase(repository),
            getProductById = GetProductByIdUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideDiscoveryUseCases(repository: DiscoveryRepository): DiscoveryUseCases {
        return DiscoveryUseCases(
            getDiscoveryItems = GetDiscoveryItemsUseCase(repository),
            addDiscoveryItem = AddDiscoveryItemUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideCartUseCases(repository: CartRepository): CartUseCases {
        return CartUseCases(
            getCartItems = GetCartItemsUseCase(repository),
            addToCart = AddToCartUseCase(repository),
            removeFromCart = RemoveFromCartUseCase(repository),
            updateQuantity = UpdateQuantityUseCase(repository),
            clearCart = ClearCartUseCase(repository)
        )
    }
}