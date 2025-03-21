package com.example.absolute_cinema.presentation.MovieDetailScreen.components

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun PersonCard(
    name: String?,
    role: String?,
    imageUrl: String?
) {
    Row(Modifier.width(200.dp), horizontalArrangement = spacedBy(10.dp)) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Person image",
            modifier = Modifier
                .height(85.dp)
                .width(60.dp),
            contentScale = ContentScale.Fit
        )
        Column {
            Text(
                text = name ?: "-",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
            Text(text = role ?: "-")
        }
    }
}