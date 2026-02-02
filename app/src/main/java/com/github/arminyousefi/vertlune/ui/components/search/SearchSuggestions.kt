package com.github.arminyousefi.vertlune.ui.components.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.arminyousefi.vertlune.ui.theme.Playfair

@OptIn( ExperimentalLayoutApi::class)
@Composable
fun SearchSuggestions(onSuggestionClick: (String) -> Unit) {
    val suggestions = listOf("Shirts", "Pants", "New Arrival", "Summer Collection", "Luxury")

    Column(modifier = Modifier.padding(24.dp)) {
        Text(
            text = "Popular Searches",
            fontFamily = Playfair,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            suggestions.forEach { tag ->
                SuggestionChip(tag = tag, onClick = { onSuggestionClick(tag) })
            }
        }
    }
}

@Composable
fun SuggestionChip(tag: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        color = Color(0xFFF5F5F5)

    ) {
        Text(
            text = tag,
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp),
            fontSize = 14.sp,
            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
            color = Color.DarkGray
        )
    }
}