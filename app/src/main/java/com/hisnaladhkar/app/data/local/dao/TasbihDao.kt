package com.hisnaladhkar.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hisnaladhkar.app.data.local.entity.TasbihCountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TasbihDao {

    @Query("SELECT * FROM tasbih_counts WHERE date = :date")
    fun observeCountsForDate(date: String): Flow<List<TasbihCountEntity>>

    @Query("SELECT count FROM tasbih_counts WHERE date = :date AND phrase = :phrase")
    suspend fun getCount(date: String, phrase: String): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: TasbihCountEntity)
}
