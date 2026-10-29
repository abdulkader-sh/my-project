package com.hisnaladhkar.app.domain.usecase

import com.hisnaladhkar.app.domain.model.Category
import com.hisnaladhkar.app.domain.model.CategoryDimension
import com.hisnaladhkar.app.domain.repository.DhikrRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesByDimensionUseCase @Inject constructor(
    private val repository: DhikrRepository
) {
    operator fun invoke(dimension: CategoryDimension): Flow<List<Category>> =
        repository.observeCategoriesByDimension(dimension)
}
