package com.example.absolute_cinema.presentation.MovieDetailScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.absolute_cinema.util.UtilFunctions.formatMoney

@Composable
fun BoxOfficeCard(
    place: String?,
    value: Int?,
    currency: String?
) {
    Card() {
        Column(Modifier.padding(vertical = 10.dp, horizontal = 15.dp).fillMaxWidth()) {
            val convertedValue = value?.let { formatMoney(it) } ?: "-"
            Text(
                text = "${currency ?: "-"} $convertedValue",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                textAlign = TextAlign.Left
            )
            Text(text = place ?: "-", textAlign = TextAlign.Left)
        }
    }
}