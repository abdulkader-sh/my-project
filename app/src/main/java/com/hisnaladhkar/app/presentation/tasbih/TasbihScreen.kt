package com.hisnaladhkar.app.presentation.tasbih

import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TasbihScreen(viewModel: TasbihViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var vibrationEnabled by remember { mutableStateOf(true) }
    val vibrator = remember { context.getSystemService(Vibrator::class.java) }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(TASBIH_PHRASES) { phrase ->
                FilterChip(
                    selected = phrase == uiState.selectedPhrase,
                    onClick = { viewModel.onSelectPhrase(phrase) },
                    label = { Text(phrase) }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Card(
                    modifier = Modifier.size(220.dp),
                    shape = CircleShape,
                    onClick = {
                        viewModel.onIncrement()
                        if (vibrationEnabled) {
                            vibrator?.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE))
                        }
                    }
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "${uiState.currentCount}", fontSize = 56.sp, style = MaterialTheme.typography.displayMedium)
                    }
                }

                Text(
                    text = uiState.selectedPhrase,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 24.dp)
                )

                Row(
                    modifier = Modifier.padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(onClick = { viewModel.onReset() }) {
                        Icon(Icons.Filled.RestartAlt, contentDescription = null)
                        Text(text = "تصفير", modifier = Modifier.padding(start = 6.dp))
                    }
                    IconToggleButton(checked = vibrationEnabled, onCheckedChange = { vibrationEnabled = it }) {
                        Icon(
                            Icons.Filled.Vibration,
                            contentDescription = if (vibrationEnabled) "إيقاف الاهتزاز" else "تفعيل الاهتزاز",
                            tint = if (vibrationEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
