package com.github.arminyousefi.vertlune.di

import android.content.Context
import androidx.room.Room
import com.github.arminyousefi.vertlune.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "vertlune_database"
        ).build()
    }

    @Provides
    fun provideProductDao(db: AppDatabase) = db.productDao()

    @Provides
    fun provideDiscoveryDao(db: AppDatabase) = db.discoveryDao()

    @Provides
    fun provideCartDao(db: AppDatabase) = db.cartDao()

}