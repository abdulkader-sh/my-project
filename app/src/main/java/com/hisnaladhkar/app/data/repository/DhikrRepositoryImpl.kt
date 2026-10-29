package com.hisnaladhkar.app.data.repository

import com.hisnaladhkar.app.data.local.dao.CategoryDao
import com.hisnaladhkar.app.data.local.dao.DhikrDao
import com.hisnaladhkar.app.data.mapper.toDomain
import com.hisnaladhkar.app.domain.model.Category
import com.hisnaladhkar.app.domain.model.CategoryDimension
import com.hisnaladhkar.app.domain.model.Dhikr
import com.hisnaladhkar.app.domain.repository.DhikrRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DhikrRepositoryImpl @Inject constructor(
    private val dhikrDao: DhikrDao,
    private val categoryDao: CategoryDao
) : DhikrRepository {

    override fun observeByCategory(categoryId: Long): Flow<List<Dhikr>> =
        dhikrDao.observeByCategory(categoryId).map { list -> list.map { it.toDomain() } }

    override fun observeById(dhikrId: Long): Flow<Dhikr?> =
        dhikrDao.observeById(dhikrId).map { it?.toDomain() }

    override fun search(query: String): Flow<List<Dhikr>> =
        dhikrDao.search(query).map { list -> list.map { it.toDomain() } }

    override fun observeCategoriesByDimension(dimension: CategoryDimension): Flow<List<Category>> =
        categoryDao.observeByDimension(dimension).map { list -> list.map { it.toDomain() } }

    override fun observeCategoryById(categoryId: Long): Flow<Category?> =
        categoryDao.observeById(categoryId).map { it?.toDomain() }
}
