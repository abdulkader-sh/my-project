package com.hisnaladhkar.app.domain.repository

import com.hisnaladhkar.app.domain.model.PrayerTimes

interface PrayerTimesRepository {
    /** يعيد null إن تعذّر الحصول على موقع معروف (مثلاً أول تشغيل قبل أي تحديد GPS سابق). */
    suspend fun getTodayPrayerTimes(): PrayerTimes?
}
