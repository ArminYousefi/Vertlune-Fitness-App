package com.github.arminyousefi.vertlune.domain.model

data class DiscoveryItem(
    val id: Int = 0,
    val title: String,
    val tag: String,
    val imageSource: DiscoveryImageSource,
    val isUserCreated: Boolean
)

sealed class DiscoveryImageSource {
    data class Asset(val path: String) : DiscoveryImageSource()
    data class UserUpload(val base64: String) : DiscoveryImageSource()
}