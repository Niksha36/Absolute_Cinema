package com.example.absolute_cinema.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "similar_movies_cross_ref",
    primaryKeys = ["movieId", "similarMovieId"],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SimilarMovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["similarMovieId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["similarMovieId"]), Index(value = ["movieId"])]
)
data class SimilarMovieCrossRef(
    val movieId: Int,
    val similarMovieId: Int
)
