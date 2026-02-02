package com.github.arminyousefi.vertlune.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.github.arminyousefi.vertlune.ui.helper.getDrawableId
import com.github.arminyousefi.vertlune.ui.theme.Playfair

@Composable
fun BestSellerCard(
    title: String,
    category: String,
    imagePath: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // بهینه‌سازی ۱: محاسبه ID تصویر فقط یک‌بار
    val imageResId = remember(imagePath) { getDrawableId(imagePath) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(450.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(32.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            // بهینه‌سازی ۲: استفاده از Coil برای لود بهینه و روان
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(imageResId)
                    .crossfade(true)
                    .allowHardware(true)
                    .build(),
                contentDescription = title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // لایه گرادینت برای خوانایی (بهینه‌سازی شده بصری)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            ),
                            // به جای مقدار فیکس 600f، از 0.5f استفاده کنید تا در تمام گوشی‌ها منعطف باشد
                            startY = 400f
                        )
                    )
            )

            // محتوای متنی
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(32.dp) // پدینگ بیشتر برای ظاهر لوکس‌تر
            ) {
                Text(
                    text = category.uppercase(),
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 32.sp,
                    fontFamily = Playfair,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 38.sp
                )
            }
        }
    }
}