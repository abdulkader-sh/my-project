package com.hisnaladhkar.app.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hisnaladhkar.app.domain.model.Dhikr

@Composable
fun DhikrCard(dhikr: Dhikr, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = dhikr.arabicText,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = dhikr.source, style = MaterialTheme.typography.labelMedium)
                dhikr.repeatCount?.let { count ->
                    Text(text = "التكرار: $count", style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }
}
