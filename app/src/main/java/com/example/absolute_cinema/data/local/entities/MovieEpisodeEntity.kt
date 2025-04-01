package com.example.absolute_cinema.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.absolute_cinema.domain.model.common.MovieRating

@Entity(tableName = "movie_episodes")
data class MovieEpisodeEntity(
    @PrimaryKey val id: Int = 0,
    val alternativeName: String?,
    val enName: String?,
    val name: String?,
    val poster: String?,
    @Embedded(prefix = "rating_") val rating: MovieRating?,
    val type: String?,
    val year: Int?
)
