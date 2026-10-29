package com.hisnaladhkar.app.domain.repository

import com.hisnaladhkar.app.domain.model.Category
import com.hisnaladhkar.app.domain.model.CategoryDimension
import com.hisnaladhkar.app.domain.model.Dhikr
import kotlinx.coroutines.flow.Flow

interface DhikrRepository {
    fun observeByCategory(categoryId: Long): Flow<List<Dhikr>>
    fun observeById(dhikrId: Long): Flow<Dhikr?>
    fun search(query: String): Flow<List<Dhikr>>
    fun observeCategoriesByDimension(dimension: CategoryDimension): Flow<List<Category>>
    fun observeCategoryById(categoryId: Long): Flow<Category?>
}
