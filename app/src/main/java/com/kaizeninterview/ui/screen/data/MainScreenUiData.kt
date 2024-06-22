package com.kaizeninterview.ui.screen.data

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Section(
    val id: String,
    val title: String,
    val items: List<Cell>,
    var isFav: Boolean = false,
    var isExpanded: Boolean = true
) {
    val itemsFiltered: List<Cell>
        get() = if (isFav) items.filter { it.isFav } else items

    fun calculateSectionHeight(): Dp {
        var rows = itemsFiltered.size / 4
        if (itemsFiltered.size % 4 > 0) {
            rows += 1
        }

        var paddings = if (rows > 1) {
            (rows * 16).dp
        } else {
            0.dp
        }

        return (rows * 210).dp - paddings
    }
}

data class Cell(
    var id: String,
    var timeUntilStart: Long,
    var isFav: Boolean = false,
    var competitor1: String,
    var competitor2: String
)