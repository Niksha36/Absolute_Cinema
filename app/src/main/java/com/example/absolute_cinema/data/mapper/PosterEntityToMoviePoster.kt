package com.example.absolute_cinema.data.mapper

import com.example.absolute_cinema.data.local.dao.MoviePosterEntity
import com.example.absolute_cinema.domain.model.MoviePoster

fun MoviePosterEntity.toMoviePoster(): MoviePoster {
    return MoviePoster(
        movieId = movieId,
        engName = engName,
        alternativeName = alternativeName,
        genres = genres,
        name = name,
        posterImg = posterImg,
        filmCritics = filmCritics,
        imdb = imdb,
        kp = kp,
        russianFilmCritics = russianFilmCritics,
        year = year,
        timestamp = timestamp,
    )
}