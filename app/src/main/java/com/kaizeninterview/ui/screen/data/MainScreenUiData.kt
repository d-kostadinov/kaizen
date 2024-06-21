package com.kaizeninterview.ui.screen.data

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Section(
    val title: String,
    val items: List<Cell>,
    var isFav: Boolean = false,
    var isExpanded: Boolean = false
) {
    fun calculateSectionHeight(): Dp {
        var rows = items.size / 4
        if (items.size % 4 > 0) {
            rows += 1
        }

        var paddings = (rows * 16).dp

        return (rows * 120).dp - paddings
    }
}

data class Cell(
    var timeUntilStart: Long,
    var isFav: Boolean = false,
    var competitor1: String,
    var competitor2: String
)