package com.example.absolute_cinema.data.remoute.dto.top_movies

import com.example.absolute_cinema.data.remoute.dto.common.Genre
import com.example.absolute_cinema.data.remoute.dto.common.Poster
import com.example.absolute_cinema.data.remoute.dto.common.Rating
import kotlinx.serialization.Serializable

@Serializable
data class Doc(
    val id: Int,
    val enName: String? = null,
    val genres: List<Genre> = emptyList(),
    val name: String? = null,
    val alternativeName: String? = null,
    val poster: Poster,
    val rating: Rating
)