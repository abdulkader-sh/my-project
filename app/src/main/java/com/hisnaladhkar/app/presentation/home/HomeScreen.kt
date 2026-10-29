package com.hisnaladhkar.app.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hisnaladhkar.app.R
import com.hisnaladhkar.app.domain.model.Category

@Composable
fun HomeScreen(
    onCategoryClick: (Category) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when {
        uiState.isLoading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        else -> LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Text(
                    text = stringResource(R.string.home_title),
                    style = MaterialTheme.typography.titleLarge
                )
            }
            if (uiState.popularCategories.isNotEmpty()) {
                item {
                    CategorySection(
                        title = "الأكثر شهرة وتداولاً",
                        categories = uiState.popularCategories,
                        onCategoryClick = onCategoryClick
                    )
                }
            }
            if (uiState.timeCategories.isNotEmpty()) {
                item {
                    CategorySection(
                        title = "حسب الوقت والمناسبة",
                        categories = uiState.timeCategories,
                        onCategoryClick = onCategoryClick
                    )
                }
            }
            if (uiState.themeCategories.isNotEmpty()) {
                item {
                    CategorySection(
                        title = "حسب المعنى والموضوع",
                        categories = uiState.themeCategories,
                        onCategoryClick = onCategoryClick
                    )
                }
            }
            if (uiState.sourceCategories.isNotEmpty()) {
                item {
                    CategorySection(
                        title = "حسب المصدر",
                        categories = uiState.sourceCategories,
                        onCategoryClick = onCategoryClick
                    )
                }
            }
        }
    }
}

@Composable
private fun CategorySection(
    title: String,
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit
) {
    Column {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories, key = { it.id }) { category ->
                CategoryChip(category = category, onClick = { onCategoryClick(category) })
            }
        }
    }
}

@Composable
private fun CategoryChip(category: Category, onClick: () -> Unit) {
    Card(onClick = onClick) {
        Text(
            text = category.nameArabic,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
