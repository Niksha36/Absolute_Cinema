package com.example.absolute_cinema.data.remoute.dto.common

import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    val filmCritics: Double?,
    val imdb: Double?,
    val kp: Double?,
    val russianFilmCritics: Double?
)