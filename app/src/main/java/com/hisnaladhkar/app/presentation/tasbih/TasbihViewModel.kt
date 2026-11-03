package com.hisnaladhkar.app.presentation.tasbih

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisnaladhkar.app.domain.usecase.GetTasbihCountsUseCase
import com.hisnaladhkar.app.domain.usecase.IncrementTasbihUseCase
import com.hisnaladhkar.app.domain.usecase.ResetTasbihUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/** العبارات المتاحة للتسبيح؛ لكل عبارة عدّاد يومي مستقل. */
val TASBIH_PHRASES = listOf("سبحان الله", "الحمد لله", "الله أكبر", "لا إله إلا الله", "أستغفر الله")

data class TasbihUiState(
    val selectedPhrase: String = TASBIH_PHRASES.first(),
    val countsByPhrase: Map<String, Int> = emptyMap()
) {
    val currentCount: Int get() = countsByPhrase[selectedPhrase] ?: 0
}

@HiltViewModel
class TasbihViewModel @Inject constructor(
    private val getTasbihCounts: GetTasbihCountsUseCase,
    private val incrementTasbih: IncrementTasbihUseCase,
    private val resetTasbih: ResetTasbihUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TasbihUiState())
    val uiState: StateFlow<TasbihUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getTasbihCounts().collect { counts ->
                val map = counts.associate { it.phrase to it.count }
                _uiState.value = _uiState.value.copy(countsByPhrase = map)
            }
        }
    }

    fun onSelectPhrase(phrase: String) {
        _uiState.value = _uiState.value.copy(selectedPhrase = phrase)
    }

    fun onIncrement() {
        val phrase = _uiState.value.selectedPhrase
        viewModelScope.launch { incrementTasbih(phrase) }
    }

    fun onReset() {
        val phrase = _uiState.value.selectedPhrase
        viewModelScope.launch { resetTasbih(phrase) }
    }
}
