package com.github.arminyousefi.vertlune.di

import com.github.arminyousefi.vertlune.data.repository.CartRepositoryImpl
import com.github.arminyousefi.vertlune.data.repository.DiscoveryRepositoryImpl
import com.github.arminyousefi.vertlune.data.repository.ProductRepositoryImpl
import com.github.arminyousefi.vertlune.domain.repository.CartRepository
import com.github.arminyousefi.vertlune.domain.repository.DiscoveryRepository
import com.github.arminyousefi.vertlune.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDiscoveryRepository(
        impl: DiscoveryRepositoryImpl
    ): DiscoveryRepository

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        impl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        impl: CartRepositoryImpl
    ): CartRepository
}