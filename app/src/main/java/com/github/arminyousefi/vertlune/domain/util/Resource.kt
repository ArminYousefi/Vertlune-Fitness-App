package com.github.arminyousefi.vertlune.domain.util

sealed class Resource<T>(
    val data: T? = null,
    val uiMessage: String? = null, // پیامی که به کاربر نمایش داده می‌شود
    val devMessage: String? = null  // لاگ فنی برای عیب‌یابی
) {
    class Success<T>(data: T) : Resource<T>(data)

    class Error<T>(
        uiMessage: String,
        devMessage: String? = null,
        data: T? = null
    ) : Resource<T>(data, uiMessage, devMessage)

    class Loading<T>(data: T? = null) : Resource<T>(data)
}