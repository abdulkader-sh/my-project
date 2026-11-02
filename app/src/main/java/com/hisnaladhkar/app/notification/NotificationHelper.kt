package com.hisnaladhkar.app.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.hisnaladhkar.app.MainActivity
import com.hisnaladhkar.app.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

const val PRAYER_NOTIFICATION_CHANNEL_ID = "prayer_times"
const val ADHKAR_REMINDER_CHANNEL_ID = "adhkar_reminders"

@Singleton
class NotificationHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun createChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val manager = context.getSystemService(NotificationManager::class.java) ?: return

        manager.createNotificationChannel(
            NotificationChannel(
                PRAYER_NOTIFICATION_CHANNEL_ID,
                context.getString(R.string.prayer_notification_channel_name),
                NotificationManager.IMPORTANCE_HIGH
            ).apply { description = context.getString(R.string.prayer_notification_channel_description) }
        )

        manager.createNotificationChannel(
            NotificationChannel(
                ADHKAR_REMINDER_CHANNEL_ID,
                context.getString(R.string.adhkar_reminder_channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply { description = context.getString(R.string.adhkar_reminder_channel_description) }
        )
    }

    fun showPrayerNotification(notificationId: Int, prayerLabel: String) {
        show(
            notificationId = notificationId,
            channelId = PRAYER_NOTIFICATION_CHANNEL_ID,
            title = context.getString(R.string.prayer_notification_title, prayerLabel),
            body = context.getString(R.string.prayer_notification_body),
            priority = NotificationCompat.PRIORITY_HIGH
        )
    }

    fun showAdhkarReminder(notificationId: Int, title: String, body: String) {
        show(
            notificationId = notificationId,
            channelId = ADHKAR_REMINDER_CHANNEL_ID,
            title = title,
            body = body,
            priority = NotificationCompat.PRIORITY_DEFAULT
        )
    }

    private fun show(notificationId: Int, channelId: String, title: String, body: String, priority: Int) {
        if (Build.VERSION.SDK_INT >= 33 &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val contentIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            notificationId,
            contentIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_popup_reminder)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(priority)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        NotificationManagerCompat.from(context).notify(notificationId, notification)
    }
}
