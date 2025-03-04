package com.example.absolute_cinema.data.remoute.dto.top_movies

import kotlinx.serialization.Serializable

@Serializable
data class TopMoviesDto(
    val docs: List<Doc>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)