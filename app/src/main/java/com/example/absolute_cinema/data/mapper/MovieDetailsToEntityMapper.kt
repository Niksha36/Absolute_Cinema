package com.example.absolute_cinema.data.mapper

import com.example.absolute_cinema.data.local.dao.MoviePlatformWithUrl
import com.example.absolute_cinema.data.local.dao.MovieWithRelations
import com.example.absolute_cinema.data.local.dao.SimilarMovieWithRating
import com.example.absolute_cinema.data.local.entities.ActorEntity
import com.example.absolute_cinema.data.local.entities.BoxOfficeByLocationEntity
import com.example.absolute_cinema.data.local.entities.BudgetEntity
import com.example.absolute_cinema.data.local.entities.CommentEntity
import com.example.absolute_cinema.data.local.entities.MovieEntity
import com.example.absolute_cinema.data.local.entities.MovieEpisodeEntity
import com.example.absolute_cinema.data.local.entities.MovieFactEntity
import com.example.absolute_cinema.data.local.entities.MovieRatingEntity
import com.example.absolute_cinema.data.local.entities.ProductionCompanyEntity
import com.example.absolute_cinema.data.local.entities.SeasonInfoEntity
import com.example.absolute_cinema.data.local.entities.SimilarMovieEntity
import com.example.absolute_cinema.data.local.entities.StreamingPlatformEntity
import com.example.absolute_cinema.domain.model.MovieComment
import com.example.absolute_cinema.domain.model.MovieDetails
import java.sql.Timestamp
import java.time.Instant


fun movieDetailsToMovieWithRelations(movie: MovieDetails, comments: List<MovieComment>): MovieWithRelations {
    return MovieWithRelations(
        movie = MovieEntity(
            id = movie.id,
            year = movie.year,
            ageRating = movie.ageRating,
            alternativeName = movie.alternativeName,
            previewImage = movie.previewImage,
            countries = movie.countries,
            description = movie.description,
            shortDescription = movie.shortDescription,
            enName = movie.enName,
            name = movie.name,
            movieLength = movie.movieLength,
            lists = movie.lists,
            poster = movie.poster,
            genres = movie.genres,
            isSeries = movie.isSeries,
            spokenLanguages = movie.spokenLanguages,
            trailer = movie.videos,
            slogan = movie.slogan,
            type = movie.type,
            timestamp = Instant.now(),
        ),
        rating = movie.rating.let {
            MovieRatingEntity(
                movieId = movie.id,
                filmCritics = it?.filmCritics,
                imdb = it?.imdb,
                kinopoisk = it?.kinopoisk,
                russianFilmCritics = it?.russianFilmCritics
            )
        },
        boxOffice = movie.boxOffice?.let {
            listOf(it.russia, it.usa, it.world).map { box ->
                BoxOfficeByLocationEntity(
                    movieId = movie.id,
                    region = box.region,
                    currency = box.currency,
                    value = box.value
                )
            }
        },
        productionCompanies = movie.productionCompanies?.map {
            ProductionCompanyEntity(
                name = it.name,
                img = it.img
            )
        },
        episodes = movie.sequelsAndPrequels?.map {
            MovieEpisodeEntity(
                alternativeName = it.alternativeName,
                enName = it.enName,
                name = it.name,
                poster = it.poster,
                rating = it.rating,
                type = it.type,
                year = it.year,
                id = it.id
            )
        },
        facts = movie.facts?.map {
            MovieFactEntity(
                movieId = movie.id,
                spoiler = it.spoiler,
                type = it.type,
                value = it.value
            )
        },
        persons = movie.persons?.map {
            ActorEntity(
                description = it.description,
                enName = it.enName,
                enProfession = it.enProfession,
                name = it.name,
                photo = it.photo,
                profession = it.profession,
                id = it.id
            )
        },
        seasonsInfo = movie.seasonsInfo?.map {
            SeasonInfoEntity(
                movieId = movie.id,
                episodesCount = it.episodesCount,
                number = it.number
            )
        },
        platformsAvailableOn = movie.platformsAvailableOn?.map {
            MoviePlatformWithUrl(
                platform = StreamingPlatformEntity(
                    logo = it.logo,
                    name = it.name
                ),
                url = it.url
            )
        },
        similarMovies = movie.similarMovies?.map {
            SimilarMovieWithRating(
                similarMovie = SimilarMovieEntity(
                    alternativeName = it.alternativeName,
                    enName = it.enName,
                    name = it.name,
                    poster = it.poster,
                    type = it.type,
                    year = it.year,
                    id = it.id
                ),
                rating = movie.rating.let {
                    MovieRatingEntity(
                        movieId = movie.id,
                        filmCritics = it?.filmCritics,
                        imdb = it?.imdb,
                        kinopoisk = it?.kinopoisk,
                        russianFilmCritics = it?.russianFilmCritics
                    )
                }
            )
        },
        budget = movie.budget.let {
            BudgetEntity(
                movieId = movie.id,
                currency = it.currency,
                value = it.value
            )
        },
        comments = comments.map{
            CommentEntity(
                author = it.author,
                authorId = it.authorId,
                date = it.date,
                movieId = movie.id,
                review = it.review,
                reviewDislikes = it.reviewDislikes,
                reviewLikes = it.reviewLikes,
                title = it.title,
                type = it.type,
                userRating = it.userRating,
                id = it.id
            )
        }

    )

}