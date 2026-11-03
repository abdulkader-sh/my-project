package com.hisnaladhkar.app.domain.usecase

import com.hisnaladhkar.app.domain.repository.TasbihRepository
import javax.inject.Inject

class IncrementTasbihUseCase @Inject constructor(
    private val repository: TasbihRepository
) {
    suspend operator fun invoke(phrase: String) = repository.increment(phrase)
}
