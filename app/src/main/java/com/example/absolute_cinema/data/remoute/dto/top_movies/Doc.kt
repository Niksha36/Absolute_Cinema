package com.example.absolute_cinema.data.remoute.dto.top_movies

import kotlinx.serialization.Serializable

@Serializable
data class Doc(
    val enName: String? = null,
    val genres: List<Genre> = emptyList(),
    val name: String? = null,
    val alternativeName: String? = null,
    val poster: Poster,
    val rating: Rating
)