package com.hisnaladhkar.app.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.hisnaladhkar.app.data.prayertimes.Prayer
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PrayerAlarmReceiver : BroadcastReceiver() {

    @Inject lateinit var notificationHelper: NotificationHelper

    @Inject lateinit var scheduler: PrayerTimeAlarmScheduler

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            ACTION_SHOW_PRAYER_NOTIFICATION -> {
                val prayerName = intent.getStringExtra(EXTRA_PRAYER_NAME) ?: return
                val prayer = runCatching { Prayer.valueOf(prayerName) }.getOrNull() ?: return
                notificationHelper.showPrayerNotification(prayer.ordinal + 1, prayer.arabicLabel)
            }
            ACTION_DAILY_REFRESH -> scheduler.scheduleAllForToday()
        }
    }
}
