package com.example.absolute_cinema.data.mapper

import com.example.absolute_cinema.data.remoute.dto.top_movies.TopMoviesDto
import com.example.absolute_cinema.domain.model.MoviePoster

fun TopMoviesDto.toMoviePosterList(): List<MoviePoster> {
    val movies = this.docs.map {
        MoviePoster(
            engName = it.enName,
            alternativeName = it.alternativeName,
            genres = it.genres.map { genre -> genre.name },
            name = it.name,
            posterImg = it.poster.url,
            filmCritics = it.rating.filmCritics,
            imdb = it.rating.imdb,
            kp = it.rating.kp,
            russianFilmCritics = it.rating.russianFilmCritics,
            movieId = it.id
        )
    }
    return movies
}