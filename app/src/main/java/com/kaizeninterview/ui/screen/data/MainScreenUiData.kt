package com.kaizeninterview.ui.screen.data

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Section(
    val title: String,
    val items: List<Cell>,
    var isFav: Boolean = false,
    var isExpanded: Boolean = true
) {
    fun calculateSectionHeight(): Dp {
        var rows = items.size / 4
        if (items.size % 4 > 0) {
            rows += 1
        }

        var paddings = if(rows > 1){
            (rows * 16).dp
        }else{
            0.dp
        }

        return (rows * 200).dp - paddings
    }
}

data class Cell(
    var timeUntilStart: Long,
    var isFav: Boolean = false,
    var competitor1: String,
    var competitor2: String
)