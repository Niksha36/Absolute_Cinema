package com.example.absolute_cinema.data.remoute.dto.comments
import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    val author: String? = null,
    val authorId: Int? = null,
    val createdAt: String? = null,
    val date: String? = null,
    val id: Int? = null,
    val movieId: Int? = null,
    val review: String? = null,
    val reviewDislikes: Int? = null,
    val reviewLikes: Int? = null,
    val title: String? = null,
    val type: String? = null,
    val updatedAt: String? = null,
    val userRating: Int? = null
)