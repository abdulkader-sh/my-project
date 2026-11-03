package com.hisnaladhkar.app.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.hisnaladhkar.app.R
import com.hisnaladhkar.app.data.prayertimes.Prayer
import com.hisnaladhkar.app.data.settings.AdhkarReminder
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
            ACTION_SHOW_ADHKAR_REMINDER -> {
                val reminderName = intent.getStringExtra(EXTRA_ADHKAR_REMINDER_NAME) ?: return
                val reminder = runCatching { AdhkarReminder.valueOf(reminderName) }.getOrNull() ?: return
                val (titleRes, bodyRes) = when (reminder) {
                    AdhkarReminder.MORNING -> R.string.adhkar_reminder_morning_title to R.string.adhkar_reminder_morning_body
                    AdhkarReminder.EVENING -> R.string.adhkar_reminder_evening_title to R.string.adhkar_reminder_evening_body
                }
                notificationHelper.showAdhkarReminder(
                    notificationId = 200 + reminder.ordinal,
                    title = context.getString(titleRes),
                    body = context.getString(bodyRes)
                )
            }
            ACTION_DAILY_REFRESH -> scheduler.scheduleAllForToday()
        }
    }
}
