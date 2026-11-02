package com.hisnaladhkar.app.data.local.entity

import androidx.room.Entity

/** عدّاد تسبيح يومي لكل عبارة على حدة؛ المفتاح الأساسي (date, phrase) يسمح بتصفير كل يوم جديد تلقائياً. */
@Entity(tableName = "tasbih_counts", primaryKeys = ["date", "phrase"])
data class TasbihCountEntity(
    val date: String, // بصيغة yyyy-MM-dd
    val phrase: String,
    val count: Int
)
