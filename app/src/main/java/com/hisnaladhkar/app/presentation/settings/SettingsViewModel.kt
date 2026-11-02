package com.hisnaladhkar.app.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisnaladhkar.app.data.settings.AdhkarReminder
import com.hisnaladhkar.app.data.settings.AdhkarReminderPrefs
import com.hisnaladhkar.app.data.settings.AppSettingsPrefs
import com.hisnaladhkar.app.data.settings.FontScale
import com.hisnaladhkar.app.data.settings.ThemeMode
import com.hisnaladhkar.app.notification.PrayerTimeAlarmScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsPrefs: AppSettingsPrefs,
    private val adhkarReminderPrefs: AdhkarReminderPrefs,
    private val scheduler: PrayerTimeAlarmScheduler
) : ViewModel() {

    val themeMode: StateFlow<ThemeMode> = settingsPrefs.themeMode
    val fontScale: StateFlow<FontScale> = settingsPrefs.fontScale

    private val _adhkarReminders = MutableStateFlow(
        AdhkarReminder.entries.associateWith { adhkarReminderPrefs.isEnabled(it) }
    )
    val adhkarReminders: StateFlow<Map<AdhkarReminder, Boolean>> = _adhkarReminders.asStateFlow()

    fun onThemeModeSelected(mode: ThemeMode) = settingsPrefs.setThemeMode(mode)

    fun onFontScaleSelected(scale: FontScale) = settingsPrefs.setFontScale(scale)

    fun onToggleAdhkarReminder(reminder: AdhkarReminder, enabled: Boolean) {
        adhkarReminderPrefs.setEnabled(reminder, enabled)
        _adhkarReminders.value = _adhkarReminders.value.toMutableMap().apply { this[reminder] = enabled }
        viewModelScope.launch { scheduler.scheduleAllForToday() }
    }
}
