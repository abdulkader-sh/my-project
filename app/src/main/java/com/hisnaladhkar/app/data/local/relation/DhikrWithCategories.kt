package com.hisnaladhkar.app.data.local.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.hisnaladhkar.app.data.local.entity.CategoryEntity
import com.hisnaladhkar.app.data.local.entity.DhikrCategoryCrossRef
import com.hisnaladhkar.app.data.local.entity.DhikrEntity

data class DhikrWithCategories(
    @Embedded val dhikr: DhikrEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = DhikrCategoryCrossRef::class,
            parentColumn = "dhikrId",
            entityColumn = "categoryId"
        )
    )
    val categories: List<CategoryEntity>
)
