package com.hisnaladhkar.app.data.repository

import com.hisnaladhkar.app.data.local.dao.TasbihDao
import com.hisnaladhkar.app.data.local.entity.TasbihCountEntity
import com.hisnaladhkar.app.domain.model.TasbihCount
import com.hisnaladhkar.app.domain.repository.TasbihRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class TasbihRepositoryImpl @Inject constructor(
    private val tasbihDao: TasbihDao
) : TasbihRepository {

    private fun today(): String = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)

    override fun observeTodayCounts(): Flow<List<TasbihCount>> =
        tasbihDao.observeCountsForDate(today()).map { list -> list.map { TasbihCount(it.phrase, it.count) } }

    override suspend fun increment(phrase: String) {
        val date = today()
        val current = tasbihDao.getCount(date, phrase) ?: 0
        tasbihDao.upsert(TasbihCountEntity(date = date, phrase = phrase, count = current + 1))
    }

    override suspend fun reset(phrase: String) {
        tasbihDao.upsert(TasbihCountEntity(date = today(), phrase = phrase, count = 0))
    }
}
