package com.example.absolute_cinema.presentation.navigation

import com.example.absolute_cinema.domain.model.MovieComment
import kotlinx.serialization.Serializable

@Serializable
sealed class Destination {
    @Serializable
    data object Home : Destination()

    @Serializable
    data class MovieDetails(val movieId: Int) : Destination()

    @Serializable
    data object Search : Destination()

    @Serializable
    data object Favorite : Destination()
    @Serializable
    data class Comment(val commentJson: String) : Destination()
}