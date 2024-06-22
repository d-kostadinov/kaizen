package com.kaizeninterview.di

import android.content.Context
import androidx.room.Room
import com.kaizeninterview.room.AppDatabase
import com.kaizeninterview.room.dao.CellFavDao
import com.kaizeninterview.room.dao.SectionFavDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideSectionFavDao(database: AppDatabase): SectionFavDao {
        return database.sectionFavDao()
    }

    @Provides
    fun provideCellFavDao(database: AppDatabase): CellFavDao {
        return database.cellFavDao()
    }
}
