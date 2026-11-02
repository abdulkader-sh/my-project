package com.hisnaladhkar.app.presentation.search

import com.hisnaladhkar.app.MainDispatcherRule
import com.hisnaladhkar.app.domain.model.AuthenticityGrade
import com.hisnaladhkar.app.domain.model.Dhikr
import com.hisnaladhkar.app.domain.model.LengthCategory
import com.hisnaladhkar.app.domain.usecase.FakeDhikrRepository
import com.hisnaladhkar.app.domain.usecase.SearchAdhkarUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private fun dhikr(id: Long, text: String) = Dhikr(
        id = id,
        arabicText = text,
        source = "اختبار",
        authenticityGrade = AuthenticityGrade.SAHIH,
        authenticityNote = null,
        fullVirtue = null,
        narrationContext = null,
        chainOfNarration = null,
        narrator = null,
        repeatCount = null,
        englishTranslation = null,
        lengthCategory = LengthCategory.SHORT,
        needsReview = false
    )

    private fun buildViewModel(repository: FakeDhikrRepository) =
        SearchViewModel(SearchAdhkarUseCase(repository))

    @Test
    fun `query shorter than two characters never triggers a search and returns no results`() = runTest {
        val repository = FakeDhikrRepository().apply { setAdhkar(listOf(dhikr(1L, "الحمد لله"))) }
        val viewModel = buildViewModel(repository)

        viewModel.onQueryChange("ا")
        advanceTimeBy(1000)

        assertTrue(viewModel.uiState.value.results.isEmpty())
    }

    @Test
    fun `query of two or more characters returns matching results after debounce`() = runTest {
        val repository = FakeDhikrRepository().apply {
            setAdhkar(listOf(dhikr(1L, "الحمد لله رب العالمين"), dhikr(2L, "سبحان الله")))
        }
        val viewModel = buildViewModel(repository)

        viewModel.onQueryChange("الحمد")
        advanceTimeBy(500)

        assertEquals(listOf(1L), viewModel.uiState.value.results.map { it.id })
    }

    @Test
    fun `rapid retyping only searches for the final query (debounce)`() = runTest {
        val repository = FakeDhikrRepository().apply {
            setAdhkar(listOf(dhikr(1L, "اللهم اغفر لي"), dhikr(2L, "اللهم ارحمني")))
        }
        val viewModel = buildViewModel(repository)

        viewModel.onQueryChange("اغ")
        advanceTimeBy(100)
        viewModel.onQueryChange("اغفر")
        advanceTimeBy(500)

        assertEquals(listOf(1L), viewModel.uiState.value.results.map { it.id })
    }
}
