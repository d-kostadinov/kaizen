package com.kaizeninterview.ui.screen.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaizeninterview.ui.theme.KaizenBlue
import com.kaizeninterview.util.formatTime
import kotlinx.coroutines.delay
import java.lang.reflect.Modifier

@Composable
fun TimerView(initialTimeInMillis: Long) {
    var timeInMillis by remember { mutableLongStateOf(initialTimeInMillis) }

    LaunchedEffect(key1 = timeInMillis) {
        while (timeInMillis > 0) {
            delay(1000L)
            timeInMillis -= 1000L
        }
    }

    Box(
        modifier = androidx.compose.ui.Modifier
            .border(
                BorderStroke(2.dp, KaizenBlue),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 6.dp, vertical = 4.dp)
    ) {
        Text(
            text = formatTime(timeInMillis),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}