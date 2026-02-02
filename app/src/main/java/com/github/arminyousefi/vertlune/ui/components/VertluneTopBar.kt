package com.github.arminyousefi.vertlune.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.LayoutGrid
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Search
import com.composables.icons.lucide.ShoppingBag
import com.github.arminyousefi.vertlune.ui.screens.CartViewModel
import com.github.arminyousefi.vertlune.ui.theme.LocalAppDimens
import com.github.arminyousefi.vertlune.ui.theme.Playfair

@Composable
fun VertluneTopBar(
    modifier: Modifier = Modifier,
    isWelcomePage: Boolean = false,
    showBack: Boolean = false,
    onBackClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    cartViewModel: CartViewModel = hiltViewModel(),
    onCartClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    val dimens = LocalAppDimens.current
    val cartItems by cartViewModel.cartItems.collectAsState()
    val hasItems = cartItems.isNotEmpty()

    val logoColor = if (isWelcomePage) {
        MaterialTheme.colorScheme.onSecondary

    } else {
        MaterialTheme.colorScheme.onBackground

    }

    val iconActionColor = MaterialTheme.colorScheme.onBackground

    val logoFontSize = if (isWelcomePage) dimens.logoSize else 24.sp
    val logoOffset = if (isWelcomePage) (-15).dp else 0.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .background(Color.Transparent)
    ) {

        Row(
            modifier = Modifier.align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showBack) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Lucide.ArrowLeft,
                        contentDescription = "Back to previous page",
                        tint = iconActionColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
            } else {
                Text(
                    text = "V",
                    fontSize = logoFontSize,
                    fontFamily = Playfair,
                    fontWeight = FontWeight.Bold,
                    color = logoColor,
                    modifier = Modifier
                        .padding(start = if (isWelcomePage) 45.dp else 24.dp)
                        .offset(y = logoOffset)
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Lucide.Search,
                    contentDescription = "Search products",
                    tint = iconActionColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            IconButton(onClick = onCartClick) {
                Box(modifier = Modifier.padding(4.dp)) {
                    Icon(
                        imageVector = Lucide.ShoppingBag,
                        contentDescription = "Cart",
                        tint = iconActionColor,
                        modifier = Modifier.size(26.dp)
                    )
                    if (hasItems) {

                        Surface(
                            modifier = Modifier
                                .size(10.dp)
                                .align(Alignment.TopEnd)
                                .offset(x = 1.dp, y = (-1).dp),
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape,
                            border = BorderStroke(
                                1.5.dp,
                                Color.White
                            )

                        ) {}
                    }
                }
            }
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Lucide.LayoutGrid,
                    contentDescription = "Open menu",
                    tint = iconActionColor,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}