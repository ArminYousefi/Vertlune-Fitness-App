package com.github.arminyousefi.vertlune.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun calculateDimensions(): AppDimensions {
    val windowInfo = LocalWindowInfo.current
    val density = LocalDensity.current

    val screenWidthPx = windowInfo.containerSize.width
    val screenWidthDp = with(density) { screenWidthPx.toDp() }.value

    return when {
        screenWidthDp < 600 -> AppDimensions()

        screenWidthDp < 840 -> AppDimensions(

            paddingMedium = 20.dp,
            logoSize = 32.sp,
            iconSize = 28.dp,
            isTablet = true
        )

        else -> AppDimensions(

            paddingMedium = 32.dp,
            logoSize = 40.sp,
            iconSize = 32.dp,
            verticalTextSize = 80.sp,
            isTablet = true
        )
    }
}