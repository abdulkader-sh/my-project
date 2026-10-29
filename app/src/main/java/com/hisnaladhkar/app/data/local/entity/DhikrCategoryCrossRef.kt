package com.hisnaladhkar.app.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

/** جدول العلاقة (Many-to-Many): ذكر واحد قد ينتمي لعدة تصنيفات عبر عدة أبعاد في آن واحد. */
@Entity(
    tableName = "dhikr_category_cross_ref",
    primaryKeys = ["dhikrId", "categoryId"],
    foreignKeys = [
        ForeignKey(entity = DhikrEntity::class, parentColumns = ["id"], childColumns = ["dhikrId"]),
        ForeignKey(entity = CategoryEntity::class, parentColumns = ["id"], childColumns = ["categoryId"])
    ],
    indices = [Index("dhikrId"), Index("categoryId")]
)
data class DhikrCategoryCrossRef(
    val dhikrId: Long,
    val categoryId: Long
)
