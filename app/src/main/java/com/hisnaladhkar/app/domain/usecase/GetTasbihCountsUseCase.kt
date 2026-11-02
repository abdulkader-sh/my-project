package com.hisnaladhkar.app.domain.usecase

import com.hisnaladhkar.app.domain.model.TasbihCount
import com.hisnaladhkar.app.domain.repository.TasbihRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasbihCountsUseCase @Inject constructor(
    private val repository: TasbihRepository
) {
    operator fun invoke(): Flow<List<TasbihCount>> = repository.observeTodayCounts()
}
