package com.example.absolute_cinema.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "similar_movies",
)
data class SimilarMovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val alternativeName: String?,
    val enName: String?,
    val name: String?,
    val poster: String?,
    val type: String?,
    val year: Int?
)