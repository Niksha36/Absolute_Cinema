package com.example.absolute_cinema.data.remoute.dto.top_movies

import kotlinx.serialization.Serializable

@Serializable
data class Doc(
    val enName: String?,
    val genres: List<Genre>,
    val name: String?,
    val alternativeName: String?,
    val poster: Poster,
    val rating: Rating
)