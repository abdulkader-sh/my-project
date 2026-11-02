package com.hisnaladhkar.app.presentation.settings

import androidx.lifecycle.ViewModel
import com.hisnaladhkar.app.data.settings.AppSettingsPrefs
import com.hisnaladhkar.app.data.settings.FontScale
import com.hisnaladhkar.app.data.settings.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsPrefs: AppSettingsPrefs
) : ViewModel() {

    val themeMode: StateFlow<ThemeMode> = settingsPrefs.themeMode
    val fontScale: StateFlow<FontScale> = settingsPrefs.fontScale

    fun onThemeModeSelected(mode: ThemeMode) = settingsPrefs.setThemeMode(mode)

    fun onFontScaleSelected(scale: FontScale) = settingsPrefs.setFontScale(scale)
}
