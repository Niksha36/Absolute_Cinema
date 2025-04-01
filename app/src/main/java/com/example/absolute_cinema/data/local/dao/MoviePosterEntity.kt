package com.example.absolute_cinema.data.local.dao

import androidx.room.DatabaseView
import java.time.Instant

@DatabaseView(
    """
        SELECT 
            id as movieId, 
            enName as engName, 
            alternativeName, 
            genres, 
            name, 
            poster as posterImg, 
            r.filmCritics, 
            r.imdb, 
            r.kinopoisk as kp, 
            r.russianFilmCritics,
            m.year,
            m.timestamp
            FROM movies m
            JOIN movie_rating r ON m.id = r.movieId

    """
)
data class MoviePosterEntity(
    val movieId: Int,
    val engName: String?,
    val alternativeName: String?,
    val genres: List<String>,
    val name: String?,
    val posterImg: String?,
    val filmCritics: Double?,
    val imdb: Double?,
    val kp: Double?,
    val russianFilmCritics: Double,
    val year: Int?,
    val timestamp: Instant
)
