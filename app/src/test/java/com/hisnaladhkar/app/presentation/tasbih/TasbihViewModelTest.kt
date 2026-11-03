package com.hisnaladhkar.app.presentation.tasbih

import com.hisnaladhkar.app.MainDispatcherRule
import com.hisnaladhkar.app.domain.usecase.FakeTasbihRepository
import com.hisnaladhkar.app.domain.usecase.GetTasbihCountsUseCase
import com.hisnaladhkar.app.domain.usecase.IncrementTasbihUseCase
import com.hisnaladhkar.app.domain.usecase.ResetTasbihUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TasbihViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private fun buildViewModel(repository: FakeTasbihRepository) = TasbihViewModel(
        getTasbihCounts = GetTasbihCountsUseCase(repository),
        incrementTasbih = IncrementTasbihUseCase(repository),
        resetTasbih = ResetTasbihUseCase(repository)
    )

    @Test
    fun `default selected phrase is the first of TASBIH_PHRASES with a zero count`() = runTest {
        val viewModel = buildViewModel(FakeTasbihRepository())

        assertEquals(TASBIH_PHRASES.first(), viewModel.uiState.value.selectedPhrase)
        assertEquals(0, viewModel.uiState.value.currentCount)
    }

    @Test
    fun `incrementing updates currentCount for the selected phrase only`() = runTest {
        val viewModel = buildViewModel(FakeTasbihRepository())

        viewModel.onIncrement()
        viewModel.onIncrement()
        testScheduler.advanceUntilIdle()

        assertEquals(2, viewModel.uiState.value.currentCount)
    }

    @Test
    fun `switching phrase shows that phrase's own independent count`() = runTest {
        val viewModel = buildViewModel(FakeTasbihRepository())

        viewModel.onIncrement()
        testScheduler.advanceUntilIdle()
        viewModel.onSelectPhrase(TASBIH_PHRASES[1])
        testScheduler.advanceUntilIdle()

        assertEquals(TASBIH_PHRASES[1], viewModel.uiState.value.selectedPhrase)
        assertEquals(0, viewModel.uiState.value.currentCount)
    }

    @Test
    fun `reset zeroes the selected phrase's count`() = runTest {
        val viewModel = buildViewModel(FakeTasbihRepository())

        viewModel.onIncrement()
        viewModel.onIncrement()
        viewModel.onReset()
        testScheduler.advanceUntilIdle()

        assertEquals(0, viewModel.uiState.value.currentCount)
    }
}
