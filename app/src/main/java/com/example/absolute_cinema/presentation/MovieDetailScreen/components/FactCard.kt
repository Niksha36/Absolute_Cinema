package com.example.absolute_cinema.presentation.MovieDetailScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import com.example.absolute_cinema.domain.model.common.MovieFact

@Composable
fun FactCard(
    fact: MovieFact,
    modifier: Modifier
) {
    Column(
        modifier
            .background(MaterialTheme.colorScheme.surfaceBright)
    ) {
        Box(Modifier.padding(10.dp)) {
            fact.value?.let {
                val cleanText = HtmlCompat.fromHtml(
                    it,
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                ).toString()

                val factStateColor = if (fact.spoiler) {
                    MaterialTheme.colorScheme.tertiaryContainer
                } else {
                    Color.White
                }
                Column {
                    Text(
                        text = if (fact.spoiler) "Спойлер!" else "Факт",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = factStateColor,
                    )
                    Text(
                        text = cleanText,
                        style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}