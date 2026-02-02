package com.github.arminyousefi.vertlune.ui.screens.discover

import android.util.Base64
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.User
import com.github.arminyousefi.vertlune.domain.model.DiscoveryImageSource
import com.github.arminyousefi.vertlune.domain.model.DiscoveryItem
import com.github.arminyousefi.vertlune.ui.helper.getDrawableId
import com.github.arminyousefi.vertlune.ui.theme.Playfair
import kotlin.math.absoluteValue

@Composable
fun DiscoveryCard(
    item: DiscoveryItem,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val imageData = remember(item.imageSource) {
        when (val source = item.imageSource) {
            is DiscoveryImageSource.Asset -> {

                getDrawableId(source.path)
            }
            is DiscoveryImageSource.UserUpload -> {

                Base64.decode(source.base64, Base64.DEFAULT)
            }
        }
    }

    val bgColor = remember(item.tag) {
        val softColors = listOf(
            Color(0xFFEFF5E1),

            Color(0xFFF1F1F1),

            Color(0xFFFEECE9),

            Color(0xFFE3F2FD),

            Color(0xFFF5F5F5)

        )

        softColors[item.tag.hashCode().absoluteValue % softColors.size]
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .padding(start = 24.dp, top = 24.dp, bottom = 24.dp, end = 140.dp)

                    .align(Alignment.CenterStart)
            ) {
                Text(
                    text = item.tag,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Gray,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontFamily = Playfair,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 32.sp
                    ),
                    color = Color.Black
                )
            }

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(imageData)
                    .crossfade(true)
                    .crossfade(500)

                    .build(),
                contentDescription = item.title,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .fillMaxHeight()
                    .width(160.dp),
                contentScale = ContentScale.Crop
            )

            if (item.isUserCreated) {
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp),
                    color = Color.Black.copy(alpha = 0.1f),
                    shape = CircleShape
                ) {
                    Icon(
                        imageVector = Lucide.User,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp).padding(4.dp),
                        tint = Color.DarkGray
                    )
                }
            }
        }
    }
}