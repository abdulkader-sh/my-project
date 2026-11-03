package com.hisnaladhkar.app.presentation.prayertimes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisnaladhkar.app.data.prayertimes.Prayer
import com.hisnaladhkar.app.data.prayertimes.PrayerNotificationPrefs
import com.hisnaladhkar.app.domain.model.PrayerTimes
import com.hisnaladhkar.app.domain.usecase.CalculatePrayerTimesUseCase
import com.hisnaladhkar.app.notification.PrayerTimeAlarmScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface PrayerTimesUiState {
    data object PermissionRequired : PrayerTimesUiState
    data object Loading : PrayerTimesUiState
    data object LocationUnavailable : PrayerTimesUiState
    data class Success(val prayerTimes: PrayerTimes) : PrayerTimesUiState
}

@HiltViewModel
class PrayerTimesViewModel @Inject constructor(
    private val calculatePrayerTimes: CalculatePrayerTimesUseCase,
    private val scheduler: PrayerTimeAlarmScheduler,
    private val notificationPrefs: PrayerNotificationPrefs
) : ViewModel() {

    private val _uiState = MutableStateFlow<PrayerTimesUiState>(PrayerTimesUiState.PermissionRequired)
    val uiState: StateFlow<PrayerTimesUiState> = _uiState.asStateFlow()

    private val _notificationToggles = MutableStateFlow(
        Prayer.entries.associateWith { notificationPrefs.isEnabled(it) }
    )
    val notificationToggles: StateFlow<Map<Prayer, Boolean>> = _notificationToggles.asStateFlow()

    fun onPermissionGranted() {
        _uiState.value = PrayerTimesUiState.Loading
        viewModelScope.launch {
            val times = calculatePrayerTimes()
            _uiState.value = if (times != null) {
                scheduler.scheduleAllForToday()
                PrayerTimesUiState.Success(times)
            } else {
                PrayerTimesUiState.LocationUnavailable
            }
        }
    }

    fun onPermissionDenied() {
        _uiState.value = PrayerTimesUiState.PermissionRequired
    }

    fun onToggleNotification(prayer: Prayer, enabled: Boolean) {
        notificationPrefs.setEnabled(prayer, enabled)
        _notificationToggles.value = _notificationToggles.value.toMutableMap().apply { this[prayer] = enabled }
        viewModelScope.launch { scheduler.scheduleAllForToday() }
    }

    fun canScheduleExactAlarms(): Boolean = scheduler.canScheduleExactAlarms()
}
