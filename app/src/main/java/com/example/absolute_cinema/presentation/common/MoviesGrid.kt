package com.example.absolute_cinema.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.absolute_cinema.domain.model.MoviePoster

@Composable
fun MoviesGrid(
    items: List<MoviePoster>,
    onNavigation: (Int) -> Unit,
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(17.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(
            start = 3.dp,
            top = 8.dp,
            end = 3.dp,
            bottom = 8.dp
        ),
    ) {
        items(items) { movie ->
            MovieCard(
                poster = movie.posterImg,
                name = movie.name ?: "",
                rating = movie.absoluteCinemaRating,
                onClick = { onNavigation(movie.movieId) }
            )
        }

    }
}