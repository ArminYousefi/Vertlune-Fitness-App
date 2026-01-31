package com.github.arminyousefi.vertlune.data.mapper

import com.github.arminyousefi.vertlune.data.local.entity.DiscoveryEntity
import com.github.arminyousefi.vertlune.data.local.entity.ProductEntity
import com.github.arminyousefi.vertlune.domain.model.DiscoveryImageSource
import com.github.arminyousefi.vertlune.domain.model.DiscoveryItem
import com.github.arminyousefi.vertlune.domain.model.Product

fun DiscoveryEntity.toDomain(): DiscoveryItem {
    val source = if (isUserCreated && imageBase64 != null) {
        DiscoveryImageSource.UserUpload(imageBase64)
    } else {
        DiscoveryImageSource.Asset(imageAssetPath ?: "default.webp")
    }
    
    return DiscoveryItem(
        id = id,
        title = mainTitle,
        tag = subTag,
        imageSource = source,
        isUserCreated = isUserCreated
    )
}

fun ProductEntity.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        category = category,
        price = price,
        description = description,
        imagePath = imagePath,
        rating = rating
    )
}