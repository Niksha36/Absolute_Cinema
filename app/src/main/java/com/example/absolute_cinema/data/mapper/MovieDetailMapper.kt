package com.example.absolute_cinema.data.mapper

import com.example.absolute_cinema.data.remoute.dto.MovieDetail.MovieDetailDto
import com.example.absolute_cinema.data.remoute.dto.common.Rating
import com.example.absolute_cinema.domain.model.MovieDetails
import com.example.absolute_cinema.domain.model.common.Actor
import com.example.absolute_cinema.domain.model.common.BoxByLocation
import com.example.absolute_cinema.domain.model.common.BoxOffice
import com.example.absolute_cinema.domain.model.common.MovieBudget
import com.example.absolute_cinema.domain.model.common.MovieEpisode
import com.example.absolute_cinema.domain.model.common.MovieFact
import com.example.absolute_cinema.domain.model.common.MovieRating
import com.example.absolute_cinema.domain.model.common.MovieTrailer
import com.example.absolute_cinema.domain.model.common.ProductionCompany
import com.example.absolute_cinema.domain.model.common.Region
import com.example.absolute_cinema.domain.model.common.SeasonInfo
import com.example.absolute_cinema.domain.model.common.SimilarMovie
import com.example.absolute_cinema.domain.model.common.StreamingPlatform
import kotlin.math.roundToInt

fun MovieDetailDto.movieDetailMapper(): MovieDetails {
    return MovieDetails(
        id = this.id,
        ageRating = this.ageRating,
        alternativeName = this.alternativeName,
        previewImage = this.backdrop?.url,
        budget = this.budget.let {
            MovieBudget(
                currency = it.currency,
                value = it.value
            )
        },
        countries = this.countries?.map {
            it.name
        },
        description = this.description,
        shortDescription = this.shortDescription,
        enName = this.enName,
        facts = this.facts?.let {
            it.map { fact ->
                MovieFact(
                    spoiler = fact.spoiler,
                    type = fact.type,
                    value = fact.value
                )
            }
        },
        boxOffice = this.fees.let {
            BoxOffice(
                russia = it?.russia.let { russia ->
                    BoxByLocation(
                        region = Region.RUSSIA,
                        currency = russia?.currency,
                        value = russia?.value?.roundToInt()
                    )
                },
                usa = it?.usa.let { usa ->
                    BoxByLocation(
                        region = Region.USA,
                        currency = usa?.currency,
                        value = usa?.value?.roundToInt()
                    )
                },
                world = it?.usa.let { world ->
                    BoxByLocation(
                        region = Region.WORLD,
                        currency = world?.currency,
                        value = world?.value?.roundToInt()
                    )
                }
            )
        },
        genres = this.genres?.map { genre ->
            genre.name
        },
        isSeries = isSeries,
        persons = persons?.map { person ->
            Actor(
                description = person.description,
                enName = person.enName,
                enProfession = person.enProfession,
                id = person.id,
                name = person.name,
                photo = person.photo,
                profession = person.profession
            )
        },
        movieLength = movieLength?.roundToInt(),
        lists = lists,
        poster = poster?.url,
        productionCompanies = productionCompanies?.map { company ->
            ProductionCompany(
                name = company.name,
                img = company.url
            )
        },
        rating = rating.movieRatingMapper(),
        slogan = slogan,
        spokenLanguages = spokenLanguages?.map { lang ->
            lang.name
        },
        type = type,
        videos = videos?.trailers?.map { trailer ->
            MovieTrailer(
                site = trailer.site,
                url = trailer.url
            )
        },
        similarMovies = similarMovies?.map { movie ->
            SimilarMovie(
                alternativeName = movie.alternativeName,
                enName = movie.enName,
                id = movie.id,
                name = movie.name,
                poster = movie.poster?.url,
                rating = movie.rating.movieRatingMapper(),
                type = movie.type,
                year = movie.year
            )
        },
        platformsAvailableOn = watchability?.items?.map { platform ->
            StreamingPlatform(
                logo = platform.logo?.url,
                name = platform.name,
                url = platform.url
            )
        },
        seasonsInfo = seasonsInfo?.map { season ->
            SeasonInfo(
                episodesCount = season.episodesCount,
                number = season.number
            )
        },
        sequelsAndPrequels = sequelsAndPrequels?.map { episode ->
            MovieEpisode(
                alternativeName = episode.alternativeName,
                enName = episode.enName,
                id = episode.id,
                name = episode.name,
                poster = episode.poster?.url,
                rating = episode.rating.movieRatingMapper(),
                type = episode.type,
                year = episode.year
            )
        },
        name = if (!name.isNullOrBlank()) {
            name
        } else if (!enName.isNullOrBlank()) {
            enName
        } else if (!alternativeName.isNullOrBlank()) {
            alternativeName
        } else {
            ""
        },
        year = year,
    )
}

private fun Rating?.movieRatingMapper(): MovieRating {
    return MovieRating(
        filmCritics = this?.filmCritics,
        imdb = this?.imdb,
        kinopoisk = this?.kp,
        russianFilmCritics = this?.russianFilmCritics
    )
}