package com.example.absolute_cinema.presentation.MovieDetailScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.absolute_cinema.presentation.MovieDetailScreen.components.MovieCharacteristics
import com.example.absolute_cinema.presentation.MovieDetailScreen.components.ParallaxMoviePoster
import com.example.absolute_cinema.util.UtilFunctions.avgRating
import com.example.compose.Absolute_CinemaTheme

@Composable
fun MovieDetailScreen(
    state: MovieDetailScreenState
) {
    val scrollState = rememberScrollState()
    val details = state.movieDetails
    if (state.error != null){
        Log.e("MovieDetailScreen", state.error)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        details?.let { movieDetails ->
            val imageUrl = if (!movieDetails.previewImage.isNullOrEmpty()) movieDetails.previewImage else movieDetails.poster
            ParallaxMoviePoster(
                poster = imageUrl,
                name = movieDetails.name,
                scrollState = scrollState
            )
            Box(
                Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(top = 5.dp)
            ) {
                Column {

                    // Список жанров
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp)
                    ) {
                        val genres = movieDetails.genres ?: emptyList()
                        items(genres) {
                            Card() {
                                Text(
                                    "#$it",
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }

                    // Характеристики
                    MovieCharacteristics(
                        countries = movieDetails.countries,
                        year = movieDetails.year,
                        languages = movieDetails.spokenLanguages,
                        ageRating = movieDetails.ageRating,
                        duration = movieDetails.movieLength,
                        rating = movieDetails.rating
                    )
                }
            }
        }
    }
}