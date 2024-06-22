package com.kaizeninterview.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaizeninterview.R
import com.kaizeninterview.ui.screen.data.Cell
import com.kaizeninterview.ui.screen.util.TimerView
import com.kaizeninterview.ui.theme.KaizenRed
import com.kaizeninterview.ui.theme.KaizenYellow
import java.util.Locale

@Composable
fun MainScreenCell(cellData: Cell, onToggleFav: (String) -> Unit) {

    var isFav by remember { mutableStateOf(false) }
    isFav = cellData.isFav

    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimerView(initialTimeInMillis = cellData.timeUntilStart)

        Spacer(modifier = Modifier.height(4.dp))
        if (isFav) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                modifier = Modifier
                    .size(SwitchDefaults.IconSize)
                    .clickable {
                        isFav = !isFav
                        onToggleFav(cellData.id)
                    },
                tint = KaizenYellow
            )
        } else {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                modifier = Modifier
                    .size(SwitchDefaults.IconSize)
                    .clickable {
                        isFav = !isFav
                        onToggleFav(cellData.id)
                    },
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp), // Adjust this height to match the height of two lines of text
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = cellData.competitor1,
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center),
                maxLines = 2
            )
        }
        Text(
            text = stringResource(R.string.vs).uppercase(Locale.ROOT),
            color = KaizenRed,
            fontSize = 9.sp
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp), // Adjust this height to match the height of two lines of text
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = cellData.competitor2,
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center),
                maxLines = 2
            )
        }
    }

}