package com.example.absolute_cinema.domain.model

import com.example.absolute_cinema.util.UtilFunctions.avgRating
import java.time.Instant

data class MoviePoster(
    val movieId: Int,
    val engName: String?,
    val alternativeName: String?,
    val genres: List<String>,
    val name: String?,
    val posterImg: String?,
    val filmCritics: Double?,
    val imdb: Double?,
    val kp: Double?,
    val russianFilmCritics: Double?,
    val year: Int? = null,
    val timestamp: Instant? = null,
    val absoluteCinemaRating: Double = avgRating(imdb, kp)
)
