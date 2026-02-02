package com.github.arminyousefi.vertlune.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.github.arminyousefi.vertlune.R

val Playfair = FontFamily(
    Font(R.font.playfair_display_regular, FontWeight.Normal),
    Font(R.font.playfair_display_medium, FontWeight.Medium),
    Font(R.font.playfair_display_bold, FontWeight.Bold)
)

val Montserrat = FontFamily(
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_medium, FontWeight.Medium)
)

val VertluneTypography = Typography(

    displayLarge = TextStyle(
        fontFamily = Playfair,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold
    ),

    titleLarge = TextStyle(
        fontFamily = Playfair,
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium
    ),

    titleMedium = TextStyle(
        fontFamily = Playfair,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium
    ),

    bodyMedium = TextStyle(
        fontFamily = Montserrat,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),

    labelMedium = TextStyle(
        fontFamily = Montserrat,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    )
)