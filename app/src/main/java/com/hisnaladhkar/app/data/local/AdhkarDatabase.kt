package com.hisnaladhkar.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hisnaladhkar.app.data.local.dao.CategoryDao
import com.hisnaladhkar.app.data.local.dao.DhikrCategoryCrossRefDao
import com.hisnaladhkar.app.data.local.dao.DhikrDao
import com.hisnaladhkar.app.data.local.dao.FavoriteDao
import com.hisnaladhkar.app.data.local.entity.CategoryEntity
import com.hisnaladhkar.app.data.local.entity.DhikrCategoryCrossRef
import com.hisnaladhkar.app.data.local.entity.DhikrEntity
import com.hisnaladhkar.app.data.local.entity.FavoriteEntity

@Database(
    entities = [
        DhikrEntity::class,
        CategoryEntity::class,
        DhikrCategoryCrossRef::class,
        FavoriteEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AdhkarDatabase : RoomDatabase() {
    abstract fun dhikrDao(): DhikrDao
    abstract fun categoryDao(): CategoryDao
    abstract fun dhikrCategoryCrossRefDao(): DhikrCategoryCrossRefDao
    abstract fun favoriteDao(): FavoriteDao
}
