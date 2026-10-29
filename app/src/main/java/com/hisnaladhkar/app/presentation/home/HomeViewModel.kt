package com.hisnaladhkar.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisnaladhkar.app.domain.model.Category
import com.hisnaladhkar.app.domain.model.CategoryDimension
import com.hisnaladhkar.app.domain.usecase.GetCategoriesByDimensionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = true,
    val timeCategories: List<Category> = emptyList(),
    val themeCategories: List<Category> = emptyList(),
    val popularCategories: List<Category> = emptyList(),
    val sourceCategories: List<Category> = emptyList()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    getCategoriesByDimension: GetCategoriesByDimensionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                getCategoriesByDimension(CategoryDimension.TIME_OCCASION),
                getCategoriesByDimension(CategoryDimension.THEME),
                getCategoriesByDimension(CategoryDimension.POPULAR),
                getCategoriesByDimension(CategoryDimension.SOURCE)
            ) { time, theme, popular, source ->
                HomeUiState(
                    isLoading = false,
                    timeCategories = time,
                    themeCategories = theme,
                    popularCategories = popular,
                    sourceCategories = source
                )
            }.collect { state -> _uiState.value = state }
        }
    }
}
