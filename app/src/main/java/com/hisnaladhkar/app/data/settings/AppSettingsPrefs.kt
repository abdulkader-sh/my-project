package com.hisnaladhkar.app.data.settings

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

private const val KEY_THEME_MODE = "theme_mode"
private const val KEY_FONT_SCALE = "font_scale"

@Singleton
class AppSettingsPrefs @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)

    private val _themeMode = MutableStateFlow(readThemeMode())
    val themeMode: StateFlow<ThemeMode> = _themeMode.asStateFlow()

    private val _fontScale = MutableStateFlow(readFontScale())
    val fontScale: StateFlow<FontScale> = _fontScale.asStateFlow()

    fun setThemeMode(mode: ThemeMode) {
        prefs.edit { putString(KEY_THEME_MODE, mode.name) }
        _themeMode.value = mode
    }

    fun setFontScale(scale: FontScale) {
        prefs.edit { putString(KEY_FONT_SCALE, scale.name) }
        _fontScale.value = scale
    }

    private fun readThemeMode(): ThemeMode =
        ThemeMode.entries.find { it.name == prefs.getString(KEY_THEME_MODE, null) } ?: ThemeMode.SYSTEM

    private fun readFontScale(): FontScale =
        FontScale.entries.find { it.name == prefs.getString(KEY_FONT_SCALE, null) } ?: FontScale.MEDIUM
}
