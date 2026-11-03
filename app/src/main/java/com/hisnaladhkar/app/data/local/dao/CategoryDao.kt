package com.hisnaladhkar.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hisnaladhkar.app.data.local.entity.CategoryEntity
import com.hisnaladhkar.app.domain.model.CategoryDimension
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(categories: List<CategoryEntity>)

    @Query("SELECT * FROM categories WHERE dimension = :dimension ORDER BY sortOrder")
    fun observeByDimension(dimension: CategoryDimension): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM categories WHERE id = :categoryId")
    fun observeById(categoryId: Long): Flow<CategoryEntity?>

    @Query("SELECT * FROM categories ORDER BY sortOrder")
    fun observeAll(): Flow<List<CategoryEntity>>
}
