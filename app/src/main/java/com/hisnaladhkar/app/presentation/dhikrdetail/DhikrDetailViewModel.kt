package com.hisnaladhkar.app.presentation.dhikrdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisnaladhkar.app.domain.model.Dhikr
import com.hisnaladhkar.app.domain.usecase.GetDhikrDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DhikrDetailUiState(
    val isLoading: Boolean = true,
    val dhikr: Dhikr? = null
)

@HiltViewModel
class DhikrDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getDhikrDetail: GetDhikrDetailUseCase
) : ViewModel() {

    private val dhikrId: Long = checkNotNull(savedStateHandle["dhikrId"])

    private val _uiState = MutableStateFlow(DhikrDetailUiState())
    val uiState: StateFlow<DhikrDetailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getDhikrDetail(dhikrId).collect { dhikr ->
                _uiState.value = DhikrDetailUiState(isLoading = false, dhikr = dhikr)
            }
        }
    }
}
