package com.hisnaladhkar.app.data.repository

import com.hisnaladhkar.app.data.prayertimes.LocationProvider
import com.hisnaladhkar.app.data.prayertimes.PrayerTimesCalculator
import com.hisnaladhkar.app.domain.model.PrayerTimes
import com.hisnaladhkar.app.domain.repository.PrayerTimesRepository
import javax.inject.Inject

class PrayerTimesRepositoryImpl @Inject constructor(
    private val locationProvider: LocationProvider,
    private val calculator: PrayerTimesCalculator
) : PrayerTimesRepository {

    override suspend fun getTodayPrayerTimes(): PrayerTimes? {
        val location = locationProvider.getLastKnownLocation() ?: return null
        return calculator.calculate(location.latitude, location.longitude)
    }
}
