package com.hisnaladhkar.app.presentation.prayertimes

import android.Manifest
import android.content.pm.PackageManager
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hisnaladhkar.app.domain.model.PrayerTimes
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale("ar"))

@Composable
fun PrayerTimesScreen(viewModel: PrayerTimesViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        val granted = results.values.any { it }
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
                permissionLauncher.launch(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                )
            }
            is PrayerTimesUiState.Loading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            is PrayerTimesUiState.LocationUnavailable -> LocationUnavailable {
                viewModel.onPermissionGranted()
            }
            is PrayerTimesUiState.Success -> PrayerTimesList(prayerTimes = state.prayerTimes)
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
            text = "لحساب مواقيت الصلاة بدقة حسب موقعك، يحتاج التطبيق إذن الوصول للموقع.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(onClick = onRequestClick) {
            Text("السماح بالوصول للموقع")
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
private fun PrayerTimesList(prayerTimes: PrayerTimes) {
    val rows = listOf(
        "الفجر" to prayerTimes.fajr,
        "الشروق" to prayerTimes.sunrise,
        "الظهر" to prayerTimes.dhuhr,
        "العصر" to prayerTimes.asr,
        "المغرب" to prayerTimes.maghrib,
        "العشاء" to prayerTimes.isha
    )
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(text = "مواقيت الصلاة اليوم", style = MaterialTheme.typography.titleLarge)
        }
        items(rows) { (label, time) ->
            PrayerTimeRow(label = label, time = time)
        }
    }
}

@Composable
private fun PrayerTimeRow(label: String, time: LocalTime) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, style = MaterialTheme.typography.titleMedium)
            Text(text = time.format(timeFormatter), style = MaterialTheme.typography.titleMedium)
        }
    }
}
