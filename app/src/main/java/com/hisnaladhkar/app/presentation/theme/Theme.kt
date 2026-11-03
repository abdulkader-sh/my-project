package com.hisnaladhkar.app.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColors = lightColorScheme(
    primary = NavyPrimary,
    onPrimary = LightSurface,
    primaryContainer = NavyPrimaryContainer,
    secondary = SilverSecondary,
    secondaryContainer = SilverSecondaryContainer,
    background = LightBackground,
    surface = LightSurface,
    onBackground = LightOnBackground,
    onSurface = LightOnBackground,
    error = ErrorColor
)

private val DarkColors = darkColorScheme(
    primary = SilverSecondary,
    onPrimary = DarkBackground,
    primaryContainer = NavyPrimaryContainer,
    secondary = NavyPrimary,
    secondaryContainer = DarkSurface,
    background = DarkBackground,
    surface = DarkSurface,
    onBackground = DarkOnBackground,
    onSurface = DarkOnBackground,
    error = ErrorColor
)

@Composable
fun HisnAlAdhkarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // نلتزم بهوية "أزرق ليلي + فضي" بدل الألوان الديناميكية لكل جهاز
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
