package com.hisnaladhkar.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hisnaladhkar.app.domain.model.AuthenticityGrade
import com.hisnaladhkar.app.domain.model.LengthCategory

@Entity(tableName = "adhkar")
data class DhikrEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
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
    val needsReview: Boolean = false
)
