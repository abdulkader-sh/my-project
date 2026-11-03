package com.hisnaladhkar.app.data.prayertimes

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/** الصلوات التي يمكن تفعيل تنبيه عندها (الشروق مستثنى عمداً، ليس وقت صلاة). */
enum class Prayer(val arabicLabel: String) {
    FAJR("الفجر"),
    DHUHR("الظهر"),
    ASR("العصر"),
    MAGHRIB("المغرب"),
    ISHA("العشاء")
}

@Singleton
class PrayerNotificationPrefs @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs = context.getSharedPreferences("prayer_notification_prefs", Context.MODE_PRIVATE)

    fun isEnabled(prayer: Prayer): Boolean = prefs.getBoolean(prayer.name, true)

    fun setEnabled(prayer: Prayer, enabled: Boolean) {
        prefs.edit { putBoolean(prayer.name, enabled) }
    }

    fun enabledPrayers(): Set<Prayer> = Prayer.entries.filter { isEnabled(it) }.toSet()
}
