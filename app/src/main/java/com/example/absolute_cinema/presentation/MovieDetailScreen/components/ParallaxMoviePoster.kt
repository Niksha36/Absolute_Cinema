package com.example.absolute_cinema.presentation.MovieDetailScreen.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun ParallaxMoviePoster(
    poster: String?,
    name: String,
    scrollState: ScrollState
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(350.dp)
    ) {
        AsyncImage(
            model = poster,
            contentDescription = "movie poster",
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    translationY = 0.5f * scrollState.value
                    alpha = 1f - ((scrollState.value.toFloat() / scrollState.maxValue) * 6.8f)
                },
            contentScale = ContentScale.Crop
        )
        Text(
            name,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.2f),
                            MaterialTheme.colorScheme.surface
                        ),
                    )
                ),
            style = MaterialTheme.typography.displaySmall.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 4,
            overflow = TextOverflow.Ellipsis
        )
    }
}