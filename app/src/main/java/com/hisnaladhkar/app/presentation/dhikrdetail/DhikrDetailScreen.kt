package com.hisnaladhkar.app.presentation.dhikrdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hisnaladhkar.app.domain.model.AuthenticityGrade
import com.hisnaladhkar.app.domain.model.Dhikr

@Composable
fun DhikrDetailScreen(viewModel: DhikrDetailViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when {
        uiState.isLoading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        uiState.dhikr == null -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("تعذّر العثور على هذا الذكر", style = MaterialTheme.typography.bodyLarge)
        }
        else -> DhikrDetailContent(dhikr = uiState.dhikr!!)
    }
}

@Composable
private fun DhikrDetailContent(dhikr: Dhikr) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            if (dhikr.needsReview) {
                NeedsReviewBanner()
            }
        }

        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = dhikr.arabicText,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }

        item { SourceRow(dhikr = dhikr) }

        dhikr.repeatCount?.let { count ->
            item { InfoSection(title = "عدد التكرار المستحب", body = "$count مرات") }
        }

        dhikr.fullVirtue?.let { virtue ->
            item { InfoSection(title = "الفضل", body = virtue) }
        }

        item {
            InfoSection(
                title = "سياق/سبب الورود",
                body = dhikr.narrationContext?.takeIf { it.isNotBlank() } ?: "لم يرد سبب ورود موثق"
            )
        }

        dhikr.chainOfNarration?.let { chain ->
            item { InfoSection(title = "السند", body = chain) }
        }

        dhikr.narrator?.let { narrator ->
            item { InfoSection(title = "الراوي", body = narrator) }
        }

        if (dhikr.categories.isNotEmpty()) {
            item {
                Column {
                    Text(text = "التصنيفات", style = MaterialTheme.typography.titleMedium)
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(dhikr.categories, key = { it.id }) { category ->
                            Surface(
                                shape = MaterialTheme.shapes.small,
                                color = MaterialTheme.colorScheme.secondaryContainer
                            ) {
                                Text(
                                    text = category.nameArabic,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SourceRow(dhikr: Dhikr) {
    Column {
        Text(text = "المصدر ودرجة الصحة", style = MaterialTheme.typography.titleMedium)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = dhikr.source, style = MaterialTheme.typography.bodyMedium)
            GradeBadge(grade = dhikr.authenticityGrade)
        }
        dhikr.authenticityNote?.let { note ->
            Text(
                text = note,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
private fun GradeBadge(grade: AuthenticityGrade) {
    val (label, color) = when (grade) {
        AuthenticityGrade.QURAN -> "آية قرآنية" to Color(0xFF2E7D32)
        AuthenticityGrade.SAHIH -> "صحيح" to Color(0xFF2E7D32)
        AuthenticityGrade.HASAN -> "حسن" to Color(0xFF9A7B00)
        AuthenticityGrade.NEEDS_REVIEW -> "يحتاج مراجعة" to Color(0xFFB3261E)
    }
    Surface(shape = MaterialTheme.shapes.small, color = color.copy(alpha = 0.15f)) {
        Text(
            text = label,
            color = color,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

@Composable
private fun NeedsReviewBanner() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFB3261E).copy(alpha = 0.1f))
    ) {
        Text(
            text = "هذا النص يحتاج مراجعة شرعية قبل الاعتماد عليه — راجع ملاحظة المصدر أدناه.",
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFFB3261E)
        )
    }
}

@Composable
private fun InfoSection(title: String, body: String) {
    Column {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
        Text(text = body, style = MaterialTheme.typography.bodyMedium)
    }
}
