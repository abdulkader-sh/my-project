package com.hisnaladhkar.app.data.prayertimes

import com.batoulapps.adhan.CalculationMethod
import com.batoulapps.adhan.Coordinates
import com.batoulapps.adhan.data.DateComponents
import com.hisnaladhkar.app.domain.model.PrayerTimes
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject
import com.batoulapps.adhan.PrayerTimes as AdhanPrayerTimes

/**
 * غلاف حول مكتبة Adhan لحساب مواقيت الصلاة فلكياً بدل حساب يدوي.
 * طريقة الحساب الافتراضية: رابطة العالم الإسلامي (معيار عالمي شائع)؛ يمكن جعلها قابلة
 * للتخصيص من شاشة الإعدادات لاحقاً (مثل أم القرى أو الهيئة المصرية العامة للمساحة).
 */
class PrayerTimesCalculator @Inject constructor() {

    fun calculate(latitude: Double, longitude: Double, date: LocalDate = LocalDate.now()): PrayerTimes {
        val coordinates = Coordinates(latitude, longitude)
        val dateComponents = DateComponents(date.year, date.monthValue, date.dayOfMonth)
        val parameters = CalculationMethod.MUSLIM_WORLD_LEAGUE.parameters
        val adhanTimes = AdhanPrayerTimes(coordinates, dateComponents, parameters)
        val zone = ZoneId.systemDefault()

        return PrayerTimes(
            fajr = adhanTimes.fajr.toInstant().atZone(zone).toLocalTime(),
            sunrise = adhanTimes.sunrise.toInstant().atZone(zone).toLocalTime(),
            dhuhr = adhanTimes.dhuhr.toInstant().atZone(zone).toLocalTime(),
            asr = adhanTimes.asr.toInstant().atZone(zone).toLocalTime(),
            maghrib = adhanTimes.maghrib.toInstant().atZone(zone).toLocalTime(),
            isha = adhanTimes.isha.toInstant().atZone(zone).toLocalTime()
        )
    }
}
