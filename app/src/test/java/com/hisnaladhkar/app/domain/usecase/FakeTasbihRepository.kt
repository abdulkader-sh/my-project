package com.hisnaladhkar.app.domain.usecase

import com.hisnaladhkar.app.domain.model.TasbihCount
import com.hisnaladhkar.app.domain.repository.TasbihRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

/** مستودع تسبيح وهمي في الذاكرة، يحاكي سلوك العدّاد اليومي الحقيقي بدون Room. */
class FakeTasbihRepository : TasbihRepository {

    private val counts = MutableStateFlow<Map<String, Int>>(emptyMap())

    override fun observeTodayCounts(): Flow<List<TasbihCount>> =
        counts.map { map -> map.map { (phrase, count) -> TasbihCount(phrase, count) } }

    override suspend fun increment(phrase: String) {
        counts.value = counts.value.toMutableMap().apply { this[phrase] = (this[phrase] ?: 0) + 1 }
    }

    override suspend fun reset(phrase: String) {
        counts.value = counts.value.toMutableMap().apply { this[phrase] = 0 }
    }
}
