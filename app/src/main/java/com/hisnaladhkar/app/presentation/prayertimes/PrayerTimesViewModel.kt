package com.hisnaladhkar.app.presentation.prayertimes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisnaladhkar.app.domain.model.PrayerTimes
import com.hisnaladhkar.app.domain.usecase.CalculatePrayerTimesUseCase
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
    private val calculatePrayerTimes: CalculatePrayerTimesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PrayerTimesUiState>(PrayerTimesUiState.PermissionRequired)
    val uiState: StateFlow<PrayerTimesUiState> = _uiState.asStateFlow()

    fun onPermissionGranted() {
        _uiState.value = PrayerTimesUiState.Loading
        viewModelScope.launch {
            val times = calculatePrayerTimes()
            _uiState.value = if (times != null) {
                PrayerTimesUiState.Success(times)
            } else {
                PrayerTimesUiState.LocationUnavailable
            }
        }
    }

    fun onPermissionDenied() {
        _uiState.value = PrayerTimesUiState.PermissionRequired
    }
}
