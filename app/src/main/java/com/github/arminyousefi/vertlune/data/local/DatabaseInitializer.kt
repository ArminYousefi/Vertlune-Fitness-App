package com.github.arminyousefi.vertlune.data.local

import android.content.Context
import com.github.arminyousefi.vertlune.data.local.dao.DiscoveryDao
import com.github.arminyousefi.vertlune.data.local.dao.ProductDao
import com.github.arminyousefi.vertlune.data.local.entity.DiscoveryEntity
import com.github.arminyousefi.vertlune.data.local.entity.ProductEntity
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class DatabaseInitializer @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val discoveryDao: DiscoveryDao,
    private val productDao: ProductDao,
    private val gson: Gson
) {

    data class InitialData(
        @SerializedName("discovery_items")
        val discoveryItems: List<DiscoveryEntity>,
        val products: List<ProductEntity>
    )

    suspend fun populateData() {
        try {

            if (discoveryDao.getCount() > 0) return

            val jsonString = context.assets.open("data/initial_data.json")
                .bufferedReader().use { it.readText() }

            val initialData: InitialData = gson.fromJson(jsonString, InitialData::class.java)

            discoveryDao.insertAll(initialData.discoveryItems)
            productDao.insertAll(initialData.products)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

