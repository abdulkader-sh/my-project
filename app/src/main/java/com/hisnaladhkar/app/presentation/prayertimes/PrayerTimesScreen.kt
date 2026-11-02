package com.hisnaladhkar.app.presentation.prayertimes

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hisnaladhkar.app.data.prayertimes.Prayer
import com.hisnaladhkar.app.domain.model.PrayerTimes
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale("ar"))

@Composable
fun PrayerTimesScreen(viewModel: PrayerTimesViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val notificationToggles by viewModel.notificationToggles.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        val granted = results[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
            results[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (granted) viewModel.onPermissionGranted() else viewModel.onPermissionDenied()
    }

    fun hasLocationPermission(): Boolean =
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED

    LaunchedEffect(Unit) {
        if (hasLocationPermission()) viewModel.onPermissionGranted()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = uiState) {
            is PrayerTimesUiState.PermissionRequired -> PermissionRequest {
                val permissions = buildList {
                    add(Manifest.permission.ACCESS_FINE_LOCATION)
                    add(Manifest.permission.ACCESS_COARSE_LOCATION)
                    if (Build.VERSION.SDK_INT >= 33) add(Manifest.permission.POST_NOTIFICATIONS)
                }
                permissionLauncher.launch(permissions.toTypedArray())
            }
            is PrayerTimesUiState.Loading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            is PrayerTimesUiState.LocationUnavailable -> LocationUnavailable {
                viewModel.onPermissionGranted()
            }
            is PrayerTimesUiState.Success -> PrayerTimesList(
                prayerTimes = state.prayerTimes,
                notificationToggles = notificationToggles,
                onToggleNotification = viewModel::onToggleNotification,
                showExactAlarmWarning = !viewModel.canScheduleExactAlarms(),
                onOpenExactAlarmSettings = {
                    if (Build.VERSION.SDK_INT >= 31) {
                        context.startActivity(
                            Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM, Uri.parse("package:${context.packageName}"))
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun PermissionRequest(onRequestClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "لحساب مواقيت الصلاة بدقة حسب موقعك وتنبيهك عند دخول وقتها، يحتاج التطبيق إذن الوصول للموقع (والإشعارات).",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(onClick = onRequestClick) {
            Text("السماح")
        }
    }
}

@Composable
private fun LocationUnavailable(onRetryClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "تعذّر تحديد موقعك حالياً. تأكد من تفعيل خدمة الموقع (GPS) في جهازك ثم حاول مجدداً.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(onClick = onRetryClick) {
            Text("إعادة المحاولة")
        }
    }
}

@Composable
private fun PrayerTimesList(
    prayerTimes: PrayerTimes,
    notificationToggles: Map<Prayer, Boolean>,
    onToggleNotification: (Prayer, Boolean) -> Unit,
    showExactAlarmWarning: Boolean,
    onOpenExactAlarmSettings: () -> Unit
) {
    val rows = listOf(
        Triple("الفجر", prayerTimes.fajr, Prayer.FAJR),
        Triple("الشروق", prayerTimes.sunrise, null),
        Triple("الظهر", prayerTimes.dhuhr, Prayer.DHUHR),
        Triple("العصر", prayerTimes.asr, Prayer.ASR),
        Triple("المغرب", prayerTimes.maghrib, Prayer.MAGHRIB),
        Triple("العشاء", prayerTimes.isha, Prayer.ISHA)
    )
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(text = "مواقيت الصلاة اليوم", style = MaterialTheme.typography.titleLarge)
        }
        if (showExactAlarmWarning) {
            item { ExactAlarmWarning(onOpenSettings = onOpenExactAlarmSettings) }
        }
        items(rows) { (label, time, prayer) ->
            PrayerTimeRow(
                label = label,
                time = time,
                notificationEnabled = prayer?.let { notificationToggles[it] } ?: false,
                showToggle = prayer != null,
                onToggle = { enabled -> prayer?.let { onToggleNotification(it, enabled) } }
            )
        }
    }
}

@Composable
private fun ExactAlarmWarning(onOpenSettings: () -> Unit) {
    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF9A7B00).copy(alpha = 0.12f))) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "لتصلك تنبيهات المواقيت في وقتها بالضبط، يحتاج التطبيق إذن \"المنبّهات والتذكيرات الدقيقة\" من إعدادات النظام.",
                style = MaterialTheme.typography.bodyMedium
            )
            TextButton(onClick = onOpenSettings, modifier = Modifier.padding(top = 8.dp)) {
                Text("فتح الإعدادات")
            }
        }
    }
}

@Composable
private fun PrayerTimeRow(
    label: String,
    time: LocalTime,
    notificationEnabled: Boolean,
    showToggle: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = label, style = MaterialTheme.typography.titleMedium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = time.format(timeFormatter),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(end = if (showToggle) 8.dp else 0.dp)
                )
                if (showToggle) {
                    Switch(checked = notificationEnabled, onCheckedChange = onToggle)
                }
            }
        }
    }
}
