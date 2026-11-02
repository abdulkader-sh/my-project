package com.hisnaladhkar.app.presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hisnaladhkar.app.data.settings.FontScale
import com.hisnaladhkar.app.data.settings.ThemeMode

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {
    val themeMode by viewModel.themeMode.collectAsStateWithLifecycle()
    val fontScale by viewModel.fontScale.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Column {
                Text(text = "المظهر", style = MaterialTheme.typography.titleLarge)
                LazyRow(
                    modifier = Modifier.padding(top = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(ThemeMode.entries.toList()) { mode ->
                        FilterChip(
                            selected = mode == themeMode,
                            onClick = { viewModel.onThemeModeSelected(mode) },
                            label = { Text(mode.arabicLabel) }
                        )
                    }
                }
            }
        }
        item {
            Column {
                Text(text = "حجم خط القراءة", style = MaterialTheme.typography.titleLarge)
                LazyRow(
                    modifier = Modifier.padding(top = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(FontScale.entries.toList()) { scale ->
                        FilterChip(
                            selected = scale == fontScale,
                            onClick = { viewModel.onFontScaleSelected(scale) },
                            label = { Text(scale.arabicLabel) }
                        )
                    }
                }
            }
        }
    }
}
