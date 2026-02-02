package com.github.arminyousefi.vertlune.ui.screens.shop.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.arminyousefi.vertlune.ui.screens.shop.ProductSort
import com.github.arminyousefi.vertlune.ui.theme.Playfair

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortBottomSheet(
    selectedSort: ProductSort,
    onSortSelected: (ProductSort) -> Unit,
    onReset: () -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        dragHandle = { BottomSheetDefaults.DragHandle(color = Color.LightGray) },
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, bottom = 40.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Sort By",
                    fontFamily = Playfair,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                TextButton(onClick = onReset) {
                    Text("Reset", color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
                }
            }

            ProductSort.entries.filter { it != ProductSort.NONE }.forEach { sortOption ->
                val label = when (sortOption) {
                    ProductSort.PRICE_LOW_TO_HIGH -> "Price: Low to High"
                    ProductSort.PRICE_HIGH_TO_LOW -> "Price: High to Low"
                    ProductSort.RATING_HIGH_TO_LOW -> "Highest Rating"
                    else -> ""
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSortSelected(sortOption) }
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = label,
                        fontSize = 16.sp,
                        fontWeight = if (selectedSort == sortOption) FontWeight.Bold else FontWeight.Normal,
                        color = if (selectedSort == sortOption) Color.Black else Color.Gray
                    )
                    RadioButton(
                        selected = selectedSort == sortOption,
                        onClick = { onSortSelected(sortOption) },
                        colors = RadioButtonDefaults.colors(selectedColor = Color.Black)
                    )
                }
            }
        }
    }
}