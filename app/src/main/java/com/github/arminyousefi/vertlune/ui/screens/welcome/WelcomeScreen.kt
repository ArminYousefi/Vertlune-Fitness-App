package com.github.arminyousefi.vertlune.ui.screens.welcome

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.arminyousefi.vertlune.R
import com.github.arminyousefi.vertlune.ui.components.VertluneActionButton
import com.github.arminyousefi.vertlune.ui.components.VertluneTopBar
import com.github.arminyousefi.vertlune.ui.theme.LocalAppDimens
import com.github.arminyousefi.vertlune.ui.theme.Playfair

@Composable
fun WelcomeScreen(
    onExploreClick: () -> Unit,
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit,
    onCartClick: () -> Unit
) {
    val dimens = LocalAppDimens.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = "main background",
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(if (dimens.isTablet) 0.65f else 0.72f),

            contentScale = ContentScale.Crop,
            alignment = Alignment.CenterStart
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.72f)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxHeight(0.48f)

                    .width(30.dp)

                    .align(Alignment.CenterStart)
                    .offset(x = 52.dp, y = 0.dp)
            ) {
                val heightInPx = size.height
                val edgePx = 50f

                val edgeRatio = if (heightInPx > 0) edgePx / heightInPx else 0.2f

                val gradientBrush = Brush.verticalGradient(
                    0.0f to Color.White,

                    edgeRatio to Color.White,

                    edgeRatio + 0.001f to Color.White.copy(0.25f),

                    1f - edgeRatio - 0.001f to Color.White.copy(0.25f),

                    1f - edgeRatio to Color.White,

                    1.0f to Color.White

                )

                drawLine(
                    brush = gradientBrush,
                    start = Offset(size.width / 2, 0f),
                    end = Offset(size.width / 2, heightInPx),
                    strokeWidth = 2.dp.toPx()

                )
            }
        }

        VertluneTopBar(
            isWelcomePage = true,

            onSearchClick = onSearchClick,
            onCartClick = onCartClick,
            onMenuClick = onMenuClick
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.28f)

                .align(Alignment.CenterEnd)
        ) {
            Text(
                text = "VERTLUNE",
                fontFamily = Playfair,
                fontSize = dimens.verticalTextSize,
                fontWeight = FontWeight.Bold,
                letterSpacing = 10.sp,

                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.Center)
                    .graphicsLayer {
                        rotationZ = -90f

                        transformOrigin = TransformOrigin.Center
                    }
                    .wrapContentWidth(unbounded = true)

            )
        }

        VertluneActionButton(
            onClick = onExploreClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .navigationBarsPadding()
                .padding(bottom = 50.dp, end = 30.dp)
        )
    }
}