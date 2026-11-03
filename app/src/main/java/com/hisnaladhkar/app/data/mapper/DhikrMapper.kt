package com.hisnaladhkar.app.data.mapper

import com.hisnaladhkar.app.data.local.entity.CategoryEntity
import com.hisnaladhkar.app.data.local.relation.DhikrWithCategories
import com.hisnaladhkar.app.domain.model.Category
import com.hisnaladhkar.app.domain.model.Dhikr

fun CategoryEntity.toDomain(): Category = Category(
    id = id,
    nameArabic = nameArabic,
    dimension = dimension,
    sortOrder = sortOrder
)

fun DhikrWithCategories.toDomain(): Dhikr = Dhikr(
    id = dhikr.id,
    arabicText = dhikr.arabicText,
    source = dhikr.source,
    authenticityGrade = dhikr.authenticityGrade,
    authenticityNote = dhikr.authenticityNote,
    fullVirtue = dhikr.fullVirtue,
    narrationContext = dhikr.narrationContext,
    chainOfNarration = dhikr.chainOfNarration,
    narrator = dhikr.narrator,
    repeatCount = dhikr.repeatCount,
    englishTranslation = dhikr.englishTranslation,
    lengthCategory = dhikr.lengthCategory,
    needsReview = dhikr.needsReview,
    categories = categories.map { it.toDomain() }
)
