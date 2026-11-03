package com.hisnaladhkar.app.domain.usecase

import com.hisnaladhkar.app.domain.repository.DhikrRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: DhikrRepository
) {
    suspend operator fun invoke(dhikrId: Long) = repository.toggleFavorite(dhikrId)
}
