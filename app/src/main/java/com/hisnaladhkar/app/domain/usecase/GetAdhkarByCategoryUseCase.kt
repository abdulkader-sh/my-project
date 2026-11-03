package com.hisnaladhkar.app.domain.usecase

import com.hisnaladhkar.app.domain.model.Dhikr
import com.hisnaladhkar.app.domain.repository.DhikrRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAdhkarByCategoryUseCase @Inject constructor(
    private val repository: DhikrRepository
) {
    operator fun invoke(categoryId: Long): Flow<List<Dhikr>> = repository.observeByCategory(categoryId)
}
