package com.hisnaladhkar.app.presentation.categorylist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisnaladhkar.app.domain.model.Dhikr
import com.hisnaladhkar.app.domain.usecase.GetAdhkarByCategoryUseCase
import com.hisnaladhkar.app.domain.usecase.GetCategoryByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CategoryListUiState(
    val isLoading: Boolean = true,
    val categoryName: String = "",
    val adhkar: List<Dhikr> = emptyList()
)

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getAdhkarByCategory: GetAdhkarByCategoryUseCase,
    getCategoryById: GetCategoryByIdUseCase
) : ViewModel() {

    private val categoryId: Long = checkNotNull(savedStateHandle["categoryId"])

    private val _uiState = MutableStateFlow(CategoryListUiState())
    val uiState: StateFlow<CategoryListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                getCategoryById(categoryId),
                getAdhkarByCategory(categoryId)
            ) { category, adhkar ->
                CategoryListUiState(
                    isLoading = false,
                    categoryName = category?.nameArabic.orEmpty(),
                    adhkar = adhkar
                )
            }.collect { state -> _uiState.value = state }
        }
    }
}
