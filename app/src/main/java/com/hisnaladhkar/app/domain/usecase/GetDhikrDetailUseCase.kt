package com.hisnaladhkar.app.domain.usecase

import com.hisnaladhkar.app.domain.model.Dhikr
import com.hisnaladhkar.app.domain.repository.DhikrRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDhikrDetailUseCase @Inject constructor(
    private val repository: DhikrRepository
) {
    operator fun invoke(dhikrId: Long): Flow<Dhikr?> = repository.observeById(dhikrId)
}
