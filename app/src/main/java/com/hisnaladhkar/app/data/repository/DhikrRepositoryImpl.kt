package com.hisnaladhkar.app.data.repository

import com.hisnaladhkar.app.data.local.dao.CategoryDao
import com.hisnaladhkar.app.data.local.dao.DhikrDao
import com.hisnaladhkar.app.data.local.dao.FavoriteDao
import com.hisnaladhkar.app.data.local.entity.FavoriteEntity
import com.hisnaladhkar.app.data.mapper.toDomain
import com.hisnaladhkar.app.domain.model.Category
import com.hisnaladhkar.app.domain.model.CategoryDimension
import com.hisnaladhkar.app.domain.model.Dhikr
import com.hisnaladhkar.app.domain.repository.DhikrRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DhikrRepositoryImpl @Inject constructor(
    private val dhikrDao: DhikrDao,
    private val categoryDao: CategoryDao,
    private val favoriteDao: FavoriteDao
) : DhikrRepository {

    override fun observeByCategory(categoryId: Long): Flow<List<Dhikr>> =
        combine(dhikrDao.observeByCategory(categoryId), favoriteDao.observeFavoriteIds()) { list, favoriteIds ->
            val favoriteIdSet = favoriteIds.toSet()
            list.map { it.toDomain().copy(isFavorite = it.dhikr.id in favoriteIdSet) }
        }

    override fun observeById(dhikrId: Long): Flow<Dhikr?> =
        combine(dhikrDao.observeById(dhikrId), favoriteDao.observeFavoriteIds()) { dhikr, favoriteIds ->
            dhikr?.toDomain()?.copy(isFavorite = dhikrId in favoriteIds)
        }

    override fun search(query: String): Flow<List<Dhikr>> =
        combine(dhikrDao.search(query), favoriteDao.observeFavoriteIds()) { list, favoriteIds ->
            val favoriteIdSet = favoriteIds.toSet()
            list.map { it.toDomain().copy(isFavorite = it.dhikr.id in favoriteIdSet) }
        }

    override fun observeCategoriesByDimension(dimension: CategoryDimension): Flow<List<Category>> =
        categoryDao.observeByDimension(dimension).map { list -> list.map { it.toDomain() } }

    override fun observeCategoryById(categoryId: Long): Flow<Category?> =
        categoryDao.observeById(categoryId).map { it?.toDomain() }

    override fun observeFavorites(): Flow<List<Dhikr>> =
        dhikrDao.observeFavorites().map { list -> list.map { it.toDomain().copy(isFavorite = true) } }

    override suspend fun toggleFavorite(dhikrId: Long) {
        if (favoriteDao.isFavorite(dhikrId)) {
            favoriteDao.deleteById(dhikrId)
        } else {
            favoriteDao.insert(FavoriteEntity(dhikrId = dhikrId, addedAt = System.currentTimeMillis()))
        }
    }
}
