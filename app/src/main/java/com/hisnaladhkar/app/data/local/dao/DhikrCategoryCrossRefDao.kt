package com.hisnaladhkar.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.hisnaladhkar.app.data.local.entity.DhikrCategoryCrossRef

@Dao
interface DhikrCategoryCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(crossRefs: List<DhikrCategoryCrossRef>)
}
