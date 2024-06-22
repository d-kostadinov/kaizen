package com.kaizeninterview.room.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cell_fav")
data class CellFav(
    @PrimaryKey val id: String,
    val isFav: Boolean
)