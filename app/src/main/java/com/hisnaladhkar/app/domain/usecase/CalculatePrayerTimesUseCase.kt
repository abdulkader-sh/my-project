package com.hisnaladhkar.app.domain.usecase

import com.hisnaladhkar.app.domain.model.PrayerTimes
import com.hisnaladhkar.app.domain.repository.PrayerTimesRepository
import javax.inject.Inject

class CalculatePrayerTimesUseCase @Inject constructor(
    private val repository: PrayerTimesRepository
) {
    suspend operator fun invoke(): PrayerTimes? = repository.getTodayPrayerTimes()
}
