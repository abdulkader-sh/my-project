package com.hisnaladhkar.app.domain.usecase

import com.hisnaladhkar.app.domain.model.Category
import com.hisnaladhkar.app.domain.model.CategoryDimension
import com.hisnaladhkar.app.domain.model.Dhikr
import com.hisnaladhkar.app.domain.repository.DhikrRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

/** مستودع وهمي للاختبارات: يحتفظ بكل شيء في الذاكرة، بلا Room أو Android. */
class FakeDhikrRepository : DhikrRepository {

    private val adhkar = MutableStateFlow<List<Dhikr>>(emptyList())
    private val favoriteIds = MutableStateFlow<Set<Long>>(emptySet())
    val toggleFavoriteCalls = mutableListOf<Long>()

    fun setAdhkar(list: List<Dhikr>) {
        adhkar.value = list
    }

    override fun observeByCategory(categoryId: Long): Flow<List<Dhikr>> =
        adhkar.map { list -> list.filter { dhikr -> dhikr.categories.any { it.id == categoryId } } }

    override fun observeById(dhikrId: Long): Flow<Dhikr?> =
        adhkar.map { list -> list.firstOrNull { it.id == dhikrId } }

    override fun search(query: String): Flow<List<Dhikr>> =
        adhkar.map { list -> list.filter { it.arabicText.contains(query) } }

    override fun observeCategoriesByDimension(dimension: CategoryDimension): Flow<List<Category>> =
        adhkar.map { list -> list.flatMap { it.categories }.filter { it.dimension == dimension }.distinctBy { it.id } }

    override fun observeCategoryById(categoryId: Long): Flow<Category?> =
        adhkar.map { list -> list.flatMap { it.categories }.firstOrNull { it.id == categoryId } }

    override fun observeFavorites(): Flow<List<Dhikr>> =
        adhkar.map { list -> list.filter { it.id in favoriteIds.value } }

    override suspend fun toggleFavorite(dhikrId: Long) {
        toggleFavoriteCalls += dhikrId
        favoriteIds.value = if (dhikrId in favoriteIds.value) {
            favoriteIds.value - dhikrId
        } else {
            favoriteIds.value + dhikrId
        }
    }
}
