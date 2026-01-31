package com.github.arminyousefi.vertlune.data.local

import android.content.Context
import com.github.arminyousefi.vertlune.data.local.dao.DiscoveryDao
import com.github.arminyousefi.vertlune.data.local.dao.ProductDao
import com.github.arminyousefi.vertlune.data.local.entity.DiscoveryEntity
import com.github.arminyousefi.vertlune.data.local.entity.ProductEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class DatabaseInitializer @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val discoveryDao: DiscoveryDao,
    private val productDao: ProductDao,
    private val gson: Gson // بهتر است Gson را هم در یک Module تعریف کرده و اینجا تزریق کنید
) {

    suspend fun populateData() {
        try {
            // ۱. بررسی وجود داده‌ها (استفاده از DAO تزریق شده)
            if (discoveryDao.getCount() > 0) return

            // ۲. خواندن فایل از Assets
            val jsonString = context.assets.open("data/initial_data.json")
                .bufferedReader().use { it.readText() }

            val dataMap: Map<String, Any> = gson.fromJson(
                jsonString, object : TypeToken<Map<String, Any>>() {}.type
            )

            // ۳. پارس و درج داده‌ها
            // پارس Discovery Items
            val discoveryJson = gson.toJson(dataMap["discovery_items"])
            val discoveryList: List<DiscoveryEntity> = gson.fromJson(
                discoveryJson, object : TypeToken<List<DiscoveryEntity>>() {}.type
            )
            discoveryDao.insertAll(discoveryList)

            // پارس Products
            val productJson = gson.toJson(dataMap["products"])
            val productList: List<ProductEntity> = gson.fromJson(
                productJson, object : TypeToken<List<ProductEntity>>() {}.type
            )
            productDao.insertAll(productList)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}