package com.hisnaladhkar.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hisnaladhkar.app.data.settings.ThemeMode
import com.hisnaladhkar.app.navigation.AdhkarNavHost
import com.hisnaladhkar.app.presentation.settings.SettingsViewModel
import com.hisnaladhkar.app.presentation.theme.HisnAlAdhkarTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppRoot()
        }
    }
}

@Composable
private fun AppRoot(settingsViewModel: SettingsViewModel = hiltViewModel()) {
    val themeMode by settingsViewModel.themeMode.collectAsStateWithLifecycle()
    val fontScale by settingsViewModel.fontScale.collectAsStateWithLifecycle()

    val darkTheme = when (themeMode) {
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
    }

    val baseDensity = LocalDensity.current
    CompositionLocalProvider(
        LocalDensity provides Density(
            density = baseDensity.density,
            fontScale = baseDensity.fontScale * fontScale.multiplier
        )
    ) {
        HisnAlAdhkarTheme(darkTheme = darkTheme) {
            Surface(modifier = Modifier.fillMaxSize()) {
                AdhkarNavHost()
            }
        }
    }
}
