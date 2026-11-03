package com.hisnaladhkar.app.data.settings

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

enum class AdhkarReminder(val arabicLabel: String) {
    MORNING("تذكير أذكار الصباح"),
    EVENING("تذكير أذكار المساء")
}

@Singleton
class AdhkarReminderPrefs @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs = context.getSharedPreferences("adhkar_reminder_prefs", Context.MODE_PRIVATE)

    fun isEnabled(reminder: AdhkarReminder): Boolean = prefs.getBoolean(reminder.name, true)

    fun setEnabled(reminder: AdhkarReminder, enabled: Boolean) {
        prefs.edit { putBoolean(reminder.name, enabled) }
    }
}
