package com.github.arminyousefi.vertlune.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

enum class VertluneThemeConfig { LIGHT, DARK, DYNAMIC }
@Composable
fun VertluneTheme(
    themeConfig: VertluneThemeConfig = VertluneThemeConfig.LIGHT,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val systemInDarkTheme = isSystemInDarkTheme()

    val dimensions = calculateDimensions()

    val colorScheme = when (themeConfig) {
        VertluneThemeConfig.DYNAMIC -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (systemInDarkTheme) dynamicDarkColorScheme(context)
                else dynamicLightColorScheme(context)
            } else {
                if (systemInDarkTheme) DarkColorScheme else LightColorScheme
            }
        }
        VertluneThemeConfig.DARK -> DarkColorScheme
        VertluneThemeConfig.LIGHT -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.setDecorFitsSystemWindows(window, false)

            val isLightBar = when (themeConfig) {
                VertluneThemeConfig.LIGHT -> true
                VertluneThemeConfig.DARK -> false
                VertluneThemeConfig.DYNAMIC -> !systemInDarkTheme
            }

            val controller = WindowCompat.getInsetsController(window, view)
            controller.isAppearanceLightStatusBars = isLightBar
            controller.isAppearanceLightNavigationBars = isLightBar
        }
    }

    CompositionLocalProvider(LocalAppDimens provides dimensions) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = VertluneTypography,
            shapes = VertluneShapes,
            content = content
        )
    }
}