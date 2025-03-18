package com.example.absolute_cinema.presentation.MovieDetailScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.absolute_cinema.data.remoute.dto.MovieDetail.Country
import com.example.absolute_cinema.domain.model.common.MovieRating
import com.example.absolute_cinema.util.UtilFunctions.avgRating
import java.time.Year

@Composable
fun MovieCharacteristics(
    countries: List<String>? = null,
    year: Int? = null,
    languages: List<String>? = null,
    ageRating: Int? = null,
    duration: Int? = null,
    rating: MovieRating? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                "Страна: ${countries?.joinToString(", ") ?: "-"}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                "Год: $year",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                "Языки: ${languages?.joinToString(", ") ?: "-"}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                "Возрастное ограничение: ${ageRating?.toString() ?: "-"}+",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                "Время: ${duration?.toString() ?: "-"} мин",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                "Рейтинг: ${
                    avgRating(
                        rating?.imdb,
                        rating?.kinopoisk
                    )
                }",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}