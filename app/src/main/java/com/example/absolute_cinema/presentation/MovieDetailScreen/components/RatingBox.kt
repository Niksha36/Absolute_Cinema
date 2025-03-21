package com.example.absolute_cinema.presentation.MovieDetailScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun RatingBox(
    rating: Double,
    color: Color
) {
    Card(
        modifier = Modifier
            .padding( vertical = 15.dp)
            .fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 35.dp)
        ) {
            Text(
                rating.toString(),
                style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold),
                color = color
            )
        }
    }
}