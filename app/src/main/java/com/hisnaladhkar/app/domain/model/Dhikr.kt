package com.hisnaladhkar.app.domain.model

/** نموذج الذكر النقي في طبقة Domain، بلا أي اعتمادية على Room أو Android. */
data class Dhikr(
    val id: Long,
    val arabicText: String,
    val source: String,
    val authenticityGrade: AuthenticityGrade,
    val authenticityNote: String?,
    val fullVirtue: String?,
    val narrationContext: String?,
    val chainOfNarration: String?,
    val narrator: String?,
    val repeatCount: Int?,
    val englishTranslation: String?,
    val lengthCategory: LengthCategory,
    val needsReview: Boolean,
    val categories: List<Category> = emptyList(),
    val isFavorite: Boolean = false
)
