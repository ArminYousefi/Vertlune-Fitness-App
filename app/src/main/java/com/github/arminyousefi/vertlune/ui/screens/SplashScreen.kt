package com.github.arminyousefi.vertlune.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.github.arminyousefi.vertlune.ui.theme.Playfair
import com.github.arminyousefi.vertlune.ui.theme.SplashGradientBottom
import com.github.arminyousefi.vertlune.ui.theme.SplashGradientTop
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {

    LaunchedEffect(Unit) {
        delay(2000)
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        SplashGradientTop,
                        SplashGradientBottom
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "V",
            fontFamily = Playfair,
            fontSize = 130.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}