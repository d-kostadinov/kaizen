package com.kaizeninterview.ui.screen.util

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomCircle(
    color: Color,
    diameter: Dp
) {
    Canvas(
        modifier = Modifier
            .size(diameter)
    ) {
        drawCircle(
            color = color,
            radius = size.minDimension/3*2,
            style = Fill
        )
    }
}