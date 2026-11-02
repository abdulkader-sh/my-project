package com.hisnaladhkar.app.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/** يعيد جدولة تنبيهات اليوم بعد إعادة إقلاع الجهاز، لأن AlarmManager يفقد منبّهاته عند الإقلاع. */
@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {

    @Inject lateinit var scheduler: PrayerTimeAlarmScheduler

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            scheduler.scheduleAllForToday()
        }
    }
}
