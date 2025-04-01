package com.example.absolute_cinema.data.mapper

import com.example.absolute_cinema.data.local.dao.MovieWithRelations
import com.example.absolute_cinema.data.local.entities.BoxOfficeByLocationEntity
import com.example.absolute_cinema.domain.model.MovieComment
import com.example.absolute_cinema.domain.model.MovieDetails
import com.example.absolute_cinema.domain.model.common.Actor
import com.example.absolute_cinema.domain.model.common.BoxByLocation
import com.example.absolute_cinema.domain.model.common.BoxOffice
import com.example.absolute_cinema.domain.model.common.MovieBudget
import com.example.absolute_cinema.domain.model.common.MovieEpisode
import com.example.absolute_cinema.domain.model.common.MovieFact
import com.example.absolute_cinema.domain.model.common.MovieRating
import com.example.absolute_cinema.domain.model.common.ProductionCompany
import com.example.absolute_cinema.domain.model.common.Region
import com.example.absolute_cinema.domain.model.common.SeasonInfo
import com.example.absolute_cinema.domain.model.common.SimilarMovie
import com.example.absolute_cinema.domain.model.common.StreamingPlatform

fun MovieWithRelations.entityToMovieDetails(): MovieDetails {
    val movieDetails = MovieDetails(
        id = movie.id,
        year = movie.year,
        ageRating = movie.ageRating,
        alternativeName = movie.alternativeName,
        previewImage = movie.previewImage,
        budget = MovieBudget(
            currency = budget?.currency,
            value = budget?.value
        ),
        countries = movie.countries,
        description = movie.description,
        shortDescription = movie.shortDescription,
        enName = movie.enName,
        name = movie.name,
        facts = facts?.map {
            MovieFact(
                spoiler = it.spoiler,
                type = it.type,
                value = it.value
            )
        },
        boxOffice = boxOffice?.let { list ->
            BoxOffice(
                russia = list.find { it.region == Region.RUSSIA }?.toBoxByLocation()
                    ?: BoxByLocation(Region.RUSSIA, null, null),
                usa = list.find { it.region == Region.USA }?.toBoxByLocation() ?: BoxByLocation(
                    Region.USA,
                    null,
                    null
                ),
                world = list.find { it.region == Region.WORLD }?.toBoxByLocation() ?: BoxByLocation(
                    Region.WORLD,
                    null,
                    null
                )
            )
        },
        genres = movie.genres,
        isSeries = movie.isSeries,
        persons = persons?.map {
            Actor(
                description = it.description,
                enName = it.enName,
                enProfession = it.enProfession,
                name = it.name,
                photo = it.photo,
                profession = it.profession,
                id = it.id
            )
        },
        movieLength = movie.movieLength,
        lists = movie.lists,
        poster = movie.poster,
        productionCompanies = productionCompanies?.map {
            ProductionCompany(
                name = it.name,
                img = it.img
            )
        },
        rating = rating?.let {
            MovieRating(
                filmCritics = it.filmCritics,
                imdb = it.imdb,
                kinopoisk = it.kinopoisk,
                russianFilmCritics = it.russianFilmCritics
            )
        },
        slogan = movie.slogan,
        spokenLanguages = movie.spokenLanguages,
        type = movie.type,
        videos = movie.trailer,
        similarMovies = similarMovies?.map {
            val similarMovie = it.similarMovie
            SimilarMovie(
                alternativeName = similarMovie.alternativeName,
                enName = similarMovie.enName,
                id = similarMovie.id,
                name = similarMovie.name,
                poster = similarMovie.poster,
                rating = it.rating.let { movieRating ->
                    MovieRating(
                        filmCritics = movieRating?.filmCritics,
                        imdb = movieRating?.imdb,
                        kinopoisk = movieRating?.kinopoisk,
                        russianFilmCritics = movieRating?.russianFilmCritics
                    )
                },
                type = similarMovie.type,
                year = similarMovie.year
            )
        },
        platformsAvailableOn = platformsAvailableOn?.map {
            StreamingPlatform(
                logo = it.platform.logo,
                name = it.platform.name,
                url = it.url
            )
        },
        seasonsInfo = seasonsInfo?.map {
            SeasonInfo(
                episodesCount = it.episodesCount,
                number = it.number
            )
        },
        sequelsAndPrequels = episodes?.map {
            MovieEpisode(
                alternativeName = it.alternativeName,
                enName = it.enName,
                name = it.name,
                poster = it.poster,
                rating = it.rating,
                type = it.type,
                year = it.year,
                id = it.id,
            )
        },
        comments = comments?.map {
            MovieComment(
                author = it.author,
                authorId = it.authorId,
                date = it.date,
                id = it.id,
                movieId = it.movieId,
                review = it.review,
                reviewDislikes = it.reviewDislikes,
                reviewLikes = it.reviewLikes,
                title = it.title,
                type = it.type,
                userRating = it.userRating
            )
        } ?: emptyList()
    )


    return movieDetails
}

fun BoxOfficeByLocationEntity.toBoxByLocation(): BoxByLocation =
    BoxByLocation(region, currency, value)