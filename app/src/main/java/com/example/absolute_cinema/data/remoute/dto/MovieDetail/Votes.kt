package com.example.absolute_cinema.data.remoute.dto.MovieDetail
import kotlinx.serialization.Serializable

@Serializable
data class Votes(
    val filmCritics: Int?,
    val imdb: Int?,
    val kp: Int?,
    val russianFilmCritics: Int?
)