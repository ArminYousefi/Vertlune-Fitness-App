package com.github.arminyousefi.vertlune.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val SplashGradientTop = Color(0xFFFF522D)

val SplashGradientBottom = Color(0xFFCC3F1F)

val VertluneOrange = Color(0xFFFF522D)

val VertluneBlack = Color(0xFF000000)
val VertluneWhite = Color(0xFFFFFFFF)

val LightBackground = Color(0xFFFFFFFF)
val LightSurface = Color(0xFFF7F7F7)

val LightOnSurface = Color(0xFF1A1A1A)

val DarkBackground = Color(0xFF121212)
val DarkSurface = Color(0xFF1E1E1E)
val DarkOnSurface = Color(0xFFE0E0E0)

val LightColorScheme = lightColorScheme(
    primary = VertluneOrange,
    onPrimary = VertluneWhite,
    secondary = VertluneBlack,
    onSecondary = VertluneWhite,
    background = LightBackground,
    onBackground = LightOnSurface,
    surface = LightSurface,
    onSurface = LightOnSurface,

    primaryContainer = VertluneOrange.copy(alpha = 0.1f),
    onPrimaryContainer = VertluneOrange
)

val DarkColorScheme = darkColorScheme(
    primary = VertluneOrange,
    onPrimary = VertluneBlack,
    secondary = VertluneWhite,
    onSecondary = VertluneBlack,
    background = DarkBackground,
    onBackground = DarkOnSurface,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    primaryContainer = VertluneOrange.copy(alpha = 0.2f),
    onPrimaryContainer = VertluneOrange
)