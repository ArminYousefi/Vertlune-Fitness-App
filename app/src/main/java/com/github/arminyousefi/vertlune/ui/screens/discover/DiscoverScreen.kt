package com.github.arminyousefi.vertlune.ui.screens.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Plus
import com.github.arminyousefi.vertlune.domain.model.DiscoveryItem
import com.github.arminyousefi.vertlune.ui.components.ErrorStateCard
import com.github.arminyousefi.vertlune.ui.components.VertluneTopBar
import com.github.arminyousefi.vertlune.ui.theme.Playfair

@Composable
fun DiscoverScreen(
    onItemClick: (DiscoveryItem) -> Unit,
    onAddClick: () -> Unit,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onCartClick: () -> Unit,
    onMenuClick: () -> Unit,
    viewModel: DiscoverViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
    ) {

        VertluneTopBar(
            isWelcomePage = false,
            showBack = true,
            onBackClick = onBackClick,
            onSearchClick = onSearchClick,
            onCartClick = onCartClick,
            onMenuClick = onMenuClick
        )

        when {
            uiState.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = colorScheme.primary)
                }
            }

            uiState.errorMessage != null -> {
                ErrorStateCard(errorMessage = uiState.errorMessage!!)
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    item(key = "header_section") {
                        Column(
                            modifier = Modifier
                                .padding(vertical = 16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Discover",
                                style = MaterialTheme.typography.displaySmall.copy(
                                    fontFamily = Playfair,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 42.sp
                                ),
                                color = colorScheme.onBackground
                            )
                            Text(
                                text = "Discover your products",
                                fontSize = 12.sp,
                                color = colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    item(key = "add_button") {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.outline,
                                    shape = RoundedCornerShape(32.dp)
                                )
                                .clickable { onAddClick() },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Lucide.Plus,
                                contentDescription = "Add New Category",
                                modifier = Modifier.size(32.dp),
                                tint = Color.Gray
                            )
                        }
                    }

                    items(uiState.items, key = { it.id }) { discoveryItem ->
                        DiscoveryCard(
                            item = discoveryItem,
                            onClick = { onItemClick(discoveryItem) }
                        )
                    }
                }
            }
        }
    }
}