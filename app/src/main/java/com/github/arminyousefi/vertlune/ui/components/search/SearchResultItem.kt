package com.github.arminyousefi.vertlune.ui.components.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.github.arminyousefi.vertlune.domain.model.Product
import com.github.arminyousefi.vertlune.ui.helper.getDrawableId
import com.github.arminyousefi.vertlune.ui.theme.Playfair

@Composable
fun SearchResultItem(product: Product, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = getDrawableId(product.imagePath),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF9F9F9)),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.padding(start = 16.dp).weight(1f)) {
            Text(
                text = product.title,
                fontFamily = Playfair,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "View Product",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )
        }

        Text(
            text = "$${product.price}",
            fontWeight = FontWeight.Bold,
            fontFamily = Playfair
        )
    }
}