package com.hisnaladhkar.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hisnaladhkar.app.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: FavoriteEntity)

    @Query("SELECT dhikrId FROM favorites")
    fun observeFavoriteIds(): Flow<List<Long>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE dhikrId = :dhikrId)")
    suspend fun isFavorite(dhikrId: Long): Boolean

    @Query("DELETE FROM favorites WHERE dhikrId = :dhikrId")
    suspend fun deleteById(dhikrId: Long)
}
