package com.kaizeninterview.room.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "section_fav")
data class SectionFav(
    @PrimaryKey val id: String,
    val isFav: Boolean
)