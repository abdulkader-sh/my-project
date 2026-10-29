package com.hisnaladhkar.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisnaladhkar.app.domain.model.Dhikr
import com.hisnaladhkar.app.domain.usecase.GetAdhkarByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = true,
    val morningAdhkar: List<Dhikr> = emptyList()
)

// معرّف تصنيف "أذكار الصباح" ثابت في بيانات البذر الأولية (انظر SeedData.CAT_MORNING).
// سيُستبدل هذا التثبيت المباشر بشاشة اختيار تصنيفات حقيقية في المرحلة 2.
private const val MORNING_CATEGORY_ID = 1L

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAdhkarByCategory: GetAdhkarByCategoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getAdhkarByCategory(MORNING_CATEGORY_ID).collect { adhkar ->
                _uiState.value = HomeUiState(isLoading = false, morningAdhkar = adhkar)
            }
        }
    }
}
