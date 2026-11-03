package com.hisnaladhkar.app.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisnaladhkar.app.domain.model.Dhikr
import com.hisnaladhkar.app.domain.usecase.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FavoritesUiState(
    val isLoading: Boolean = true,
    val favorites: List<Dhikr> = emptyList()
)

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    getFavorites: GetFavoritesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getFavorites().collect { favorites ->
                _uiState.value = FavoritesUiState(isLoading = false, favorites = favorites)
            }
        }
    }
}
