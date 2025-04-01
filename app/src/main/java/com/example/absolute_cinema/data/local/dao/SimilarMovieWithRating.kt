package com.example.absolute_cinema.data.local.dao

import androidx.room.DatabaseView
import androidx.room.Embedded
import androidx.room.Relation
import com.example.absolute_cinema.data.local.entities.MovieRatingEntity
import com.example.absolute_cinema.data.local.entities.SimilarMovieEntity

@DatabaseView(
    viewName = "similar_movie_with_rating_view",
    value = """
        SELECT 
            s.id AS id,
            s.alternativeName AS alternativeName,
            s.enName AS enName,
            s.name AS name,
            s.poster AS poster,
            s.type AS type,
            s.year AS year,
            r.movieId AS movieId,
            r.filmCritics AS filmCritics,
            r.imdb AS imdb,
            r.kinopoisk AS kinopoisk,
            r.russianFilmCritics AS russianFilmCritics
        FROM similar_movies s
        LEFT JOIN movie_rating r ON s.id = r.movieId
    """
)
data class SimilarMovieWithRating(
    @Embedded val similarMovie: SimilarMovieEntity,
    @Embedded
    val rating: MovieRatingEntity?
)
