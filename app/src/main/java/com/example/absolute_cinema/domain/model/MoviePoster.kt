package com.example.absolute_cinema.domain.model

data class MoviePoster(
    val engName: String?,
    val alternativeName: String?,
    val genres: List<String>,
    val name: String?,
    val posterImg: String,
    val filmCritics: Double?,
    val imdb: Double?,
    val kp: Double?,
    val russianFilmCritics: Int?
)
