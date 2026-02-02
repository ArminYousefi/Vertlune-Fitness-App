package com.github.arminyousefi.vertlune.data.repository

import com.github.arminyousefi.vertlune.data.local.dao.DiscoveryDao
import com.github.arminyousefi.vertlune.data.local.entity.DiscoveryEntity
import com.github.arminyousefi.vertlune.data.mapper.toDomain
import com.github.arminyousefi.vertlune.domain.model.DiscoveryImageSource
import com.github.arminyousefi.vertlune.domain.model.DiscoveryItem
import com.github.arminyousefi.vertlune.domain.repository.DiscoveryRepository
import com.github.arminyousefi.vertlune.domain.util.Resource
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

@Singleton
class DiscoveryRepositoryImpl @Inject constructor(
    private val discoveryDao: DiscoveryDao
) : DiscoveryRepository {

    override fun getDiscoveryItems(): Flow<Resource<List<DiscoveryItem>>> =
        discoveryDao.getAllDiscoveryItems().map { entities ->
            val domainItems = entities.map { it.toDomain() }
            Resource.Success(domainItems) as Resource<List<DiscoveryItem>>
        }.onStart { emit(Resource.Loading()) }.catch { e ->
            emit(
                Resource.Error(
                    uiMessage = "failed to load discoveries try again later",
                    devMessage = "getDiscoveryItems DB Error: ${e.localizedMessage}"
                )
            )
        }

    override suspend fun addDiscoveryItem(item: DiscoveryItem): Resource<Unit> {
        return try {
            if (item.title.isBlank()) {
                return Resource.Error(
                    uiMessage = "title cannot be empty", devMessage = "Validation Error: Title is blank"
                )
            }
            if (item.tag.isBlank()) {
                return Resource.Error(
                    uiMessage = "tag cannot be empty",
                    devMessage = "Validation Error: Tag is blank"
                )
            }
            val entity = DiscoveryEntity(
                mainTitle = item.title,
                subTag = item.tag,
                imageAssetPath = (item.imageSource as? DiscoveryImageSource.Asset)?.path,
                imageBase64 = (item.imageSource as? DiscoveryImageSource.UserUpload)?.base64,
                isUserCreated = item.isUserCreated
            )
            discoveryDao.insertAll(listOf(entity))
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(
                uiMessage = "failed to add discovery try again later",
                devMessage = "addDiscoveryItem Failure: ${e.message}"
            )
        }
    }
}