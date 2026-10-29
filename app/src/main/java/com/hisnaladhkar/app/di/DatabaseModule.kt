package com.hisnaladhkar.app.di

import android.content.Context
import androidx.room.Room
import com.hisnaladhkar.app.data.local.AdhkarDatabase
import com.hisnaladhkar.app.data.local.dao.CategoryDao
import com.hisnaladhkar.app.data.local.dao.DhikrCategoryCrossRefDao
import com.hisnaladhkar.app.data.local.dao.DhikrDao
import com.hisnaladhkar.app.data.local.dao.FavoriteDao
import com.hisnaladhkar.app.data.local.seed.SeedDatabaseCallback
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
    fun provideDatabase(
        @ApplicationContext context: Context,
        callback: SeedDatabaseCallback
    ): AdhkarDatabase =
        Room.databaseBuilder(context, AdhkarDatabase::class.java, "adhkar.db")
            .addCallback(callback)
            .build()

    @Provides
    fun provideDhikrDao(database: AdhkarDatabase): DhikrDao = database.dhikrDao()

    @Provides
    fun provideCategoryDao(database: AdhkarDatabase): CategoryDao = database.categoryDao()

    @Provides
    fun provideDhikrCategoryCrossRefDao(database: AdhkarDatabase): DhikrCategoryCrossRefDao =
        database.dhikrCategoryCrossRefDao()

    @Provides
    fun provideFavoriteDao(database: AdhkarDatabase): FavoriteDao = database.favoriteDao()
}
