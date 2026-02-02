package com.github.arminyousefi.vertlune.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class AppDimensions(
    val paddingSmall: Dp = 8.dp,
    val paddingMedium: Dp = 16.dp,
    val paddingLarge: Dp = 24.dp,
    val logoSize: TextUnit = 70.sp,
    val iconSize: Dp = 24.dp,
    val verticalTextSize: TextUnit = 70.sp,

    val isTablet: Boolean = false
)

val LocalAppDimens = staticCompositionLocalOf { AppDimensions() }