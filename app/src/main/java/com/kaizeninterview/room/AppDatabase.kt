package com.kaizeninterview.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kaizeninterview.room.dao.CellFavDao
import com.kaizeninterview.room.dao.SectionFavDao
import com.kaizeninterview.room.table.CellFav
import com.kaizeninterview.room.table.SectionFav

@Database(entities = [SectionFav::class, CellFav::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sectionFavDao(): SectionFavDao
    abstract fun cellFavDao(): CellFavDao
}
