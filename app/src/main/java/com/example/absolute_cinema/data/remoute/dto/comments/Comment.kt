package com.example.absolute_cinema.data.remoute.dto.comments

data class Comment(
    val author: String,
    val authorId: Int,
    val createdAt: String,
    val date: String,
    val id: Int,
    val movieId: Int,
    val review: String,
    val reviewDislikes: Int,
    val reviewLikes: Int,
    val title: String,
    val type: String,
    val updatedAt: String,
    val userRating: Int
)