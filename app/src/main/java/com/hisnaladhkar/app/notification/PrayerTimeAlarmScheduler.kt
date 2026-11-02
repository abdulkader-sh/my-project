package com.hisnaladhkar.app.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.hisnaladhkar.app.data.prayertimes.LocationProvider
import com.hisnaladhkar.app.data.prayertimes.Prayer
import com.hisnaladhkar.app.data.prayertimes.PrayerNotificationPrefs
import com.hisnaladhkar.app.data.prayertimes.PrayerTimesCalculator
import com.hisnaladhkar.app.domain.model.PrayerTimes
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import javax.inject.Inject
import javax.inject.Singleton

const val ACTION_SHOW_PRAYER_NOTIFICATION = "com.hisnaladhkar.app.action.SHOW_PRAYER_NOTIFICATION"
const val ACTION_DAILY_REFRESH = "com.hisnaladhkar.app.action.DAILY_REFRESH"
const val EXTRA_PRAYER_NAME = "prayer_name"

private const val DAILY_REFRESH_REQUEST_CODE = 1000

/**
 * يحسب مواقيت اليوم (عبر Adhan + آخر موقع معروف) ويجدوِل تنبيهاً دقيقاً (AlarmManager)
 * لكل صلاة مفعَّلة لم يفت وقتها بعد، بالإضافة إلى منبِّه يومي بعد منتصف الليل بقليل
 * يعيد تكرار العملية لليوم التالي تلقائياً.
 */
@Singleton
class PrayerTimeAlarmScheduler @Inject constructor(
    @ApplicationContext private val context: Context,
    private val locationProvider: LocationProvider,
    private val calculator: PrayerTimesCalculator,
    private val prefs: PrayerNotificationPrefs
) {
    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    fun canScheduleExactAlarms(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            alarmManager?.canScheduleExactAlarms() == true
        } else {
            true
        }
    }

    /** يُستدعى عند فتح شاشة المواقيت، وعند الإقلاع، وعند المنبِّه اليومي. */
    fun scheduleAllForToday() {
        val location = locationProvider.getLastKnownLocation() ?: return
        val today = LocalDate.now()
        val prayerTimes = calculator.calculate(location.latitude, location.longitude, today)
        val enabled = prefs.enabledPrayers()

        prayerForEachTime(prayerTimes).forEach { (prayer, time) ->
            cancelAlarm(prayer)
            if (prayer in enabled) {
                val triggerAt = LocalDateTime.of(today, time)
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli()
                if (triggerAt > System.currentTimeMillis()) {
                    scheduleExactAlarm(requestCode(prayer), triggerAt, showNotificationIntent(prayer))
                }
            }
        }

        scheduleDailyRefresh(today)
    }

    private fun prayerForEachTime(prayerTimes: PrayerTimes): List<Pair<Prayer, LocalTime>> = listOf(
        Prayer.FAJR to prayerTimes.fajr,
        Prayer.DHUHR to prayerTimes.dhuhr,
        Prayer.ASR to prayerTimes.asr,
        Prayer.MAGHRIB to prayerTimes.maghrib,
        Prayer.ISHA to prayerTimes.isha
    )

    private fun scheduleDailyRefresh(today: LocalDate) {
        val nextRefresh = LocalDateTime.of(today.plusDays(1), LocalTime.of(0, 5))
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
        val intent = Intent(context, PrayerAlarmReceiver::class.java).apply { action = ACTION_DAILY_REFRESH }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            DAILY_REFRESH_REQUEST_CODE,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        scheduleExactAlarm(DAILY_REFRESH_REQUEST_CODE, nextRefresh, pendingIntent)
    }

    private fun showNotificationIntent(prayer: Prayer): PendingIntent {
        val intent = Intent(context, PrayerAlarmReceiver::class.java).apply {
            action = ACTION_SHOW_PRAYER_NOTIFICATION
            putExtra(EXTRA_PRAYER_NAME, prayer.name)
        }
        return PendingIntent.getBroadcast(
            context,
            requestCode(prayer),
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun scheduleExactAlarm(requestCode: Int, triggerAtMillis: Long, pendingIntent: PendingIntent) {
        if (!canScheduleExactAlarms()) return
        alarmManager?.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
    }

    private fun cancelAlarm(prayer: Prayer) {
        alarmManager?.cancel(showNotificationIntent(prayer))
    }

    private fun requestCode(prayer: Prayer): Int = prayer.ordinal + 1
}
