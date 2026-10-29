package com.hisnaladhkar.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.hisnaladhkar.app.data.local.entity.DhikrEntity
import com.hisnaladhkar.app.data.local.relation.DhikrWithCategories
import kotlinx.coroutines.flow.Flow

@Dao
interface DhikrDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(adhkar: List<DhikrEntity>)

    @Transaction
    @Query("SELECT * FROM adhkar WHERE id = :dhikrId")
    fun observeById(dhikrId: Long): Flow<DhikrWithCategories?>

    @Transaction
    @Query(
        """
        SELECT adhkar.* FROM adhkar
        INNER JOIN dhikr_category_cross_ref ON adhkar.id = dhikr_category_cross_ref.dhikrId
        WHERE dhikr_category_cross_ref.categoryId = :categoryId
        """
    )
    fun observeByCategory(categoryId: Long): Flow<List<DhikrWithCategories>>

    @Transaction
    @Query("SELECT * FROM adhkar WHERE arabicText LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<DhikrWithCategories>>
}
