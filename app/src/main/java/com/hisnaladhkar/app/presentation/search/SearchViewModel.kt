package com.hisnaladhkar.app.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisnaladhkar.app.domain.model.Dhikr
import com.hisnaladhkar.app.domain.usecase.SearchAdhkarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class SearchUiState(
    val query: String = "",
    val results: List<Dhikr> = emptyList(),
    val isSearching: Boolean = false
)

private const val MIN_QUERY_LENGTH = 2
private const val DEBOUNCE_MILLIS = 300L

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchAdhkar: SearchAdhkarUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    init {
        _uiState
            .map { it.query }
            .distinctUntilChanged()
            .debounce(DEBOUNCE_MILLIS)
            .flatMapLatest { query ->
                if (query.trim().length < MIN_QUERY_LENGTH) {
                    flowOf(emptyList())
                } else {
                    searchAdhkar(query.trim())
                }
            }
            .onEach { results -> _uiState.value = _uiState.value.copy(results = results, isSearching = false) }
            .launchIn(viewModelScope)
    }

    fun onQueryChange(newQuery: String) {
        _uiState.value = _uiState.value.copy(
            query = newQuery,
            isSearching = newQuery.trim().length >= MIN_QUERY_LENGTH
        )
    }
}
