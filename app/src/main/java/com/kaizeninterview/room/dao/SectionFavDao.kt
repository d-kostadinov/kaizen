package com.kaizeninterview.room.dao

import androidx.room.*
import com.kaizeninterview.room.table.CellFav
import com.kaizeninterview.room.table.SectionFav


@Dao
interface SectionFavDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sectionFav: SectionFav)

    @Delete
    suspend fun delete(sectionFav: SectionFav)

    @Update
    suspend fun update(sectionFav: SectionFav)

    @Query("SELECT * FROM section_fav WHERE id = :id")
    suspend fun getSectionFavById(id: String): SectionFav?
}


@Dao
interface CellFavDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cellFav: CellFav)

    @Delete
    suspend fun delete(cellFav: CellFav)

    @Update
    suspend fun update(cellFav: CellFav)

    @Query("SELECT * FROM cell_fav WHERE id = :id")
    suspend fun getCellFavById(id: String): CellFav?
}