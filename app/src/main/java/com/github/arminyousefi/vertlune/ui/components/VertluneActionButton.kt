package com.github.arminyousefi.vertlune.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun VertluneActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primary

) {
    Button(
        onClick = onClick,
        modifier = modifier.size(70.dp),

        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        contentPadding = PaddingValues(0.dp),
        elevation = ButtonDefaults.buttonElevation(0.dp)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(22.dp)

        ) {
            val strokeWidth = 2.dp.toPx()

            val w = size.width
            val h = size.height
            val arrowSize = w * 0.4f

            drawLine(
                color = Color.White,
                start = Offset(x = -22.dp.toPx(), y = h / 2),
                end = Offset(x = w, y = h / 2),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Butt
            )

            drawLine(
                color = Color.White,
                start = Offset(x = w, y = h / 2),
                end = Offset(x = w - (arrowSize * 1.5f), y = h / 2 - arrowSize),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )

            drawLine(
                color = Color.White,
                start = Offset(x = w, y = h / 2),
                end = Offset(x = w - (arrowSize * 1.5f), y = h / 2 + arrowSize),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )
        }
    }
}