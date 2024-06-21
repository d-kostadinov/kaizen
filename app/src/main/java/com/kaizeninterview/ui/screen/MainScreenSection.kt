package com.kaizeninterview.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaizeninterview.ui.screen.data.Section
import com.kaizeninterview.ui.screen.util.CustomCircle
import com.kaizeninterview.ui.theme.KaizenRed
import java.util.Locale

@Composable
fun MainScreenSection(section: Section, isFav: Boolean, onToggleExpand: () -> Unit, onFavClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggleExpand() }
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CustomCircle(color = KaizenRed, diameter = 16.dp)
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = section.title.uppercase(Locale.ROOT),
                fontSize = 16.sp,
                style = MaterialTheme.typography.titleLarge
            )

        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(1.dp),
                checked = isFav,
                onCheckedChange = {
                    onFavClicked()
                },
                thumbContent =
                {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                }
            )
            Icon(
                Icons.Filled.ArrowDropDown,
                contentDescription = "Expand/Collapse",
                modifier = Modifier.size(48.dp)
            )
        }
    }
}