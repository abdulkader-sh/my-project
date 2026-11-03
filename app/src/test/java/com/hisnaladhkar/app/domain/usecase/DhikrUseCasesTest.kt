package com.hisnaladhkar.app.domain.usecase

import com.hisnaladhkar.app.domain.model.AuthenticityGrade
import com.hisnaladhkar.app.domain.model.Category
import com.hisnaladhkar.app.domain.model.CategoryDimension
import com.hisnaladhkar.app.domain.model.Dhikr
import com.hisnaladhkar.app.domain.model.LengthCategory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DhikrUseCasesTest {

    private lateinit var repository: FakeDhikrRepository

    private val morningCategory = Category(1L, "أذكار الصباح", CategoryDimension.TIME_OCCASION, 1)
    private val protectionCategory = Category(2L, "التحصين", CategoryDimension.THEME, 2)

    @Before
    fun setUp() {
        repository = FakeDhikrRepository()
        repository.setAdhkar(
            listOf(
                dhikr(1L, "أصبحنا وأصبح الملك لله", listOf(morningCategory)),
                dhikr(2L, "أعوذ بكلمات الله التامات", listOf(protectionCategory)),
                dhikr(3L, "سبحان الله وبحمده", listOf(morningCategory, protectionCategory))
            )
        )
    }

    @Test
    fun `GetAdhkarByCategoryUseCase returns only adhkar linked to that category`() = runBlocking {
        val useCase = GetAdhkarByCategoryUseCase(repository)

        val result = useCase(morningCategory.id).first()

        assertEquals(setOf(1L, 3L), result.map { it.id }.toSet())
    }

    @Test
    fun `SearchAdhkarUseCase filters by Arabic text substring`() = runBlocking {
        val useCase = SearchAdhkarUseCase(repository)

        val result = useCase("سبحان").first()

        assertEquals(listOf(3L), result.map { it.id })
    }

    @Test
    fun `ToggleFavoriteUseCase delegates to repository with the given id`() = runBlocking {
        val useCase = ToggleFavoriteUseCase(repository)

        useCase(2L)

        assertEquals(listOf(2L), repository.toggleFavoriteCalls)
    }

    @Test
    fun `toggling favorite twice makes the dhikr appear then disappear from favorites`() = runBlocking {
        val toggle = ToggleFavoriteUseCase(repository)
        val getFavorites = GetFavoritesUseCase(repository)

        assertTrue(getFavorites().first().isEmpty())

        toggle(1L)
        assertEquals(listOf(1L), getFavorites().first().map { it.id })

        toggle(1L)
        assertTrue(getFavorites().first().isEmpty())
    }

    private fun dhikr(id: Long, text: String, categories: List<Category>) = Dhikr(
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
        needsReview = false,
        categories = categories
    )
}
