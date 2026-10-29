package com.hisnaladhkar.app.domain.model

data class Category(
    val id: Long,
    val nameArabic: String,
    val dimension: CategoryDimension,
    val sortOrder: Int
)
