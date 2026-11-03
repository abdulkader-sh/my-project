package com.hisnaladhkar.app.domain.repository

import com.hisnaladhkar.app.domain.model.TasbihCount
import kotlinx.coroutines.flow.Flow

interface TasbihRepository {
    /** أعداد تسبيح اليوم الحالي (تتصفّر تلقائياً عند تغيّر التاريخ لأن المفتاح يشمل التاريخ). */
    fun observeTodayCounts(): Flow<List<TasbihCount>>
    suspend fun increment(phrase: String)
    suspend fun reset(phrase: String)
}
