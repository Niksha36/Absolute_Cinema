package com.example.absolute_cinema.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "comments", foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        )
    ], indices = [Index(value = ["movieId"])]
)
data class CommentEntity(
    @PrimaryKey
    val id: Int = 0,
    val author: String?,
    val authorId: Int?,
    val date: String,
    val movieId: Int?,
    val review: String?,
    val reviewDislikes: Int?,
    val reviewLikes: Int?,
    val title: String?,
    val type: String?,
    val userRating: Double?
)
