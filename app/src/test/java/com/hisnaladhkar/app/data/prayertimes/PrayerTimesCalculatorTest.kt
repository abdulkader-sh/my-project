package com.hisnaladhkar.app.data.prayertimes

import java.time.LocalDate
import org.junit.Assert.assertTrue
import org.junit.Test

class PrayerTimesCalculatorTest {

    private val calculator = PrayerTimesCalculator()

    @Test
    fun `prayer times for Makkah are in correct chronological order`() {
        // إحداثيات مكة المكرمة، تاريخ ثابت لتفادي اعتماد الاختبار على تاريخ التشغيل.
        val result = calculator.calculate(
            latitude = 21.3891,
            longitude = 39.8579,
            date = LocalDate.of(2025, 6, 15)
        )

        assertTrue("الفجر يجب أن يسبق الشروق", result.fajr.isBefore(result.sunrise))
        assertTrue("الشروق يجب أن يسبق الظهر", result.sunrise.isBefore(result.dhuhr))
        assertTrue("الظهر يجب أن يسبق العصر", result.dhuhr.isBefore(result.asr))
        assertTrue("العصر يجب أن يسبق المغرب", result.asr.isBefore(result.maghrib))
        assertTrue("المغرب يجب أن يسبق العشاء", result.maghrib.isBefore(result.isha))
    }

    @Test
    fun `calculating twice for the same inputs yields identical results`() {
        val date = LocalDate.of(2025, 1, 1)
        val first = calculator.calculate(latitude = 24.7136, longitude = 46.6753, date = date)
        val second = calculator.calculate(latitude = 24.7136, longitude = 46.6753, date = date)

        assertTrue(first == second)
    }
}
