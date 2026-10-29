package com.hisnaladhkar.app.domain.usecase

import com.hisnaladhkar.app.domain.model.Category
import com.hisnaladhkar.app.domain.repository.DhikrRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryByIdUseCase @Inject constructor(
    private val repository: DhikrRepository
) {
    operator fun invoke(categoryId: Long): Flow<Category?> = repository.observeCategoryById(categoryId)
}
