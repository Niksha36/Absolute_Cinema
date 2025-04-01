package com.example.absolute_cinema.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "movie_rating",
    primaryKeys = ["movieId"],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MovieRatingEntity(
    val movieId: Int,
    val filmCritics: Double?,
    val imdb: Double?,
    val kinopoisk: Double?,
    val russianFilmCritics: Double?
)
