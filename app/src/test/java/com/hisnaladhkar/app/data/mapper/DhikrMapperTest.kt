package com.hisnaladhkar.app.data.mapper

import com.hisnaladhkar.app.data.local.entity.CategoryEntity
import com.hisnaladhkar.app.data.local.entity.DhikrEntity
import com.hisnaladhkar.app.data.local.relation.DhikrWithCategories
import com.hisnaladhkar.app.domain.model.AuthenticityGrade
import com.hisnaladhkar.app.domain.model.CategoryDimension
import com.hisnaladhkar.app.domain.model.LengthCategory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class DhikrMapperTest {

    @Test
    fun `CategoryEntity toDomain copies every field`() {
        val entity = CategoryEntity(id = 7L, nameArabic = "القرآن الكريم", dimension = CategoryDimension.SOURCE, sortOrder = 3)

        val domain = entity.toDomain()

        assertEquals(7L, domain.id)
        assertEquals("القرآن الكريم", domain.nameArabic)
        assertEquals(CategoryDimension.SOURCE, domain.dimension)
        assertEquals(3, domain.sortOrder)
    }

    @Test
    fun `DhikrWithCategories toDomain maps fields and nested categories, favorite defaults to false`() {
        val dhikrEntity = DhikrEntity(
            id = 42L,
            arabicText = "سبحان الله وبحمده",
            source = "مسلم",
            authenticityGrade = AuthenticityGrade.SAHIH,
            authenticityNote = null,
            fullVirtue = "حطت خطاياه",
            narrationContext = null,
            chainOfNarration = null,
            narrator = "أبو هريرة",
            repeatCount = 100,
            englishTranslation = null,
            lengthCategory = LengthCategory.SHORT,
            needsReview = false
        )
        val categoryEntity = CategoryEntity(id = 220L, nameArabic = "التسبيح", dimension = CategoryDimension.THEME, sortOrder = 1)
        val withCategories = DhikrWithCategories(dhikr = dhikrEntity, categories = listOf(categoryEntity))

        val domain = withCategories.toDomain()

        assertEquals(42L, domain.id)
        assertEquals("سبحان الله وبحمده", domain.arabicText)
        assertEquals("مسلم", domain.source)
        assertEquals(AuthenticityGrade.SAHIH, domain.authenticityGrade)
        assertEquals("أبو هريرة", domain.narrator)
        assertEquals(100, domain.repeatCount)
        assertEquals(1, domain.categories.size)
        assertEquals(220L, domain.categories.first().id)
        // isFavorite ليس عموداً في DhikrEntity نفسه، إنما يُدمج لاحقاً من FavoriteDao في الـ Repository؛
        // المابر وحده دائماً يعيد false هنا.
        assertFalse(domain.isFavorite)
    }
}
