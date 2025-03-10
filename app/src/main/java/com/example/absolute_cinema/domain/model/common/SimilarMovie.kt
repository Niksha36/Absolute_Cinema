package com.example.absolute_cinema.domain.model.common

data class SimilarMovie(
    val alternativeName: String?,
    val enName: String?,
    val id: Int,
    val name: String?,
    val poster: String?,
    val rating: MovieRating?,
    val type: String?,
    val year: Int?
)
