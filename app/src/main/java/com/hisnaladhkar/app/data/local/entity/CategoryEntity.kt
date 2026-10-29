package com.hisnaladhkar.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hisnaladhkar.app.domain.model.CategoryDimension

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nameArabic: String,
    val dimension: CategoryDimension,
    val sortOrder: Int
)
