package com.kaizeninterview.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaizeninterview.R
import com.kaizeninterview.ui.screen.data.Cell
import com.kaizeninterview.ui.theme.KaizenRed
import com.kaizeninterview.ui.theme.KaizenYellow
import java.util.Locale

@Composable
fun MainScreenCell(cellData: Cell) {

    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = cellData.timeUntilStart.toString(), color = Color.White)

        if (cellData.isFav) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
                tint = KaizenYellow
            )
        } else {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
                tint = Color.White
            )
        }
        Text(text = cellData.competitor1, fontSize = 14.sp, color = Color.White)
        Text(
            text = stringResource(R.string.vs).uppercase(Locale.ROOT),
            color = KaizenRed,
            fontSize = 9.sp
        )
        Text(text = cellData.competitor2, fontSize = 14.sp, color = Color.White)
    }

}