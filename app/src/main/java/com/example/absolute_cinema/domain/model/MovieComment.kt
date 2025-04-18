package com.example.absolute_cinema.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieComment(
    val author: String?,
    val authorId: Int?,
    val date: String,
    val id: Int,
    val movieId: Int?,
    val review: String?,
    val reviewDislikes: Int?,
    val reviewLikes: Int?,
    val title: String?,
    val type: String?,
    val userRating: Double?
)
