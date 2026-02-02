package com.github.arminyousefi.vertlune.ui.screens.shop

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.composables.icons.lucide.ListFilter
import com.composables.icons.lucide.Lucide
import com.github.arminyousefi.vertlune.domain.util.mapper.toCartItem
import com.github.arminyousefi.vertlune.ui.components.ErrorStateCard
import com.github.arminyousefi.vertlune.ui.components.VertluneTopBar
import com.github.arminyousefi.vertlune.ui.components.snackbar.SnackbarType
import com.github.arminyousefi.vertlune.ui.screens.CartViewModel
import com.github.arminyousefi.vertlune.ui.screens.UiEvent
import com.github.arminyousefi.vertlune.ui.screens.shop.components.FilterBottomSheet
import com.github.arminyousefi.vertlune.ui.screens.shop.components.ShopProductItem
import com.github.arminyousefi.vertlune.ui.screens.shop.components.SortBottomSheet
import com.github.arminyousefi.vertlune.ui.theme.Playfair
import kotlinx.coroutines.flow.merge

@Composable
fun ShopScreen(
    onShowSnackbar: (String, SnackbarType) -> Unit,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onCartClick: () -> Unit,
    onMenuClick: () -> Unit,
    viewModel: ShopViewModel,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val cartItems by cartViewModel.cartItems.collectAsState()

    var showSortSheet by remember { mutableStateOf(false) }
    var showFilterSheet by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        merge(viewModel.eventFlow, cartViewModel.eventFlow).collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> onShowSnackbar(event.message, event.type)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        VertluneTopBar(
            isWelcomePage = false,
            showBack = true,
            onBackClick = onBackClick,
            onSearchClick = onSearchClick,
            onCartClick = onCartClick,
            onMenuClick = onMenuClick
        )

        Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)) {
            Text(
                text = "Shop",
                fontFamily = Playfair,
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Hollywood Hairstyles Do Not",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                onClick = { showSortSheet = true },
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
            ) {
                Icon(imageVector = Lucide.ListFilter, contentDescription = null, tint = Color.Black)
                Spacer(Modifier.width(8.dp))
                Text("Sort", color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { showFilterSheet = true },
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("Filter", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Black
                    )
                }

                uiState.errorMessage != null -> {
                    ErrorStateCard(errorMessage = uiState.errorMessage ?: "Unknown Error")
                }

                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 24.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        items(uiState.products, key = { it.id }) { product ->
                            val cartItem = cartItems.find { it.productId == product.id }
                            val currentQuantity = cartItem?.quantity ?: 0

                            ShopProductItem(
                                title = product.title,
                                price = product.price,
                                imagePath = product.imagePath,
                                rating = product.rating,
                                quantityInCart = currentQuantity,
                                onIncrease = {
                                    if (currentQuantity == 0) cartViewModel.addToCart(product.toCartItem())
                                    else cartViewModel.updateQuantity(product.id, currentQuantity + 1)
                                },
                                onDecrease = {
                                    if (currentQuantity > 1) cartViewModel.updateQuantity(product.id, currentQuantity - 1)
                                    else cartViewModel.removeFromCart(product.id)
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    if (showSortSheet) {
        SortBottomSheet(
            selectedSort = uiState.currentSort,
            onSortSelected = { viewModel.applySort(it); showSortSheet = false },
            onReset = { viewModel.resetSort(); showSortSheet = false },
            onDismiss = { showSortSheet = false }
        )
    }

    if (showFilterSheet) {
        FilterBottomSheet(
            selectedFilter = uiState.currentFilter,
            onFilterSelected = { viewModel.applyFilter(it); showFilterSheet = false },
            onReset = { viewModel.resetFilter(); showFilterSheet = false },
            onDismiss = { showFilterSheet = false }
        )
    }
}