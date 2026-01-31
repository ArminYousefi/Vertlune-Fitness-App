package com.github.arminyousefi.vertlune.data.repository

import com.github.arminyousefi.vertlune.data.local.dao.ProductDao
import com.github.arminyousefi.vertlune.data.mapper.toDomain
import com.github.arminyousefi.vertlune.domain.model.Product
import com.github.arminyousefi.vertlune.domain.repository.ProductRepository
import com.github.arminyousefi.vertlune.domain.util.Resource
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
) : ProductRepository {

    override fun getAllProducts(): Flow<Resource<List<Product>>> =
        productDao.getAllProducts()
            .map { entities ->
                val domainList = entities.map { it.toDomain() }
                Resource.Success(domainList) as Resource<List<Product>>
            }
            .onStart { emit(Resource.Loading()) }
            .catch { e ->
                emit(Resource.Error(
                    uiMessage = "failed to fetch products",
                    devMessage = "getAllProducts DB failure: ${e.localizedMessage}"
                ))
            }

    override fun getProductsByCategory(category: String): Flow<Resource<List<Product>>> =
        productDao.getProductsByCategory(category)
            .map { entities ->
                Resource.Success(entities.map { it.toDomain() }) as Resource<List<Product>>
            }
            .onStart { emit(Resource.Loading()) }
            .catch { e ->
                emit(Resource.Error(
                    uiMessage = "failed to fetch products",
                    devMessage = "getProductsByCategory ($category) failure: ${e.localizedMessage}"
                ))
            }

    override suspend fun getProductById(id: String): Resource<Product> {
        return try {
            val product = productDao.getProductById(id)?.toDomain()
            if (product != null) {
                Resource.Success(product)
            } else {
                Resource.Error(
                    uiMessage = "product not found",
                    devMessage = "getProductById null for ID: $id"
                )
            }
        } catch (e: Exception) {
            Resource.Error(
                uiMessage = "failed to fetch products",
                devMessage = "getProductById Exception: ${e.stackTraceToString()}"
            )
        }
    }
}