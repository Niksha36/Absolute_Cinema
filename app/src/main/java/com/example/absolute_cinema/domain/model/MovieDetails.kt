package com.example.absolute_cinema.domain.model


import com.example.absolute_cinema.domain.model.common.Actor
import com.example.absolute_cinema.domain.model.common.BoxOffice
import com.example.absolute_cinema.domain.model.common.MovieBudget
import com.example.absolute_cinema.domain.model.common.MovieEpisode
import com.example.absolute_cinema.domain.model.common.MovieFact
import com.example.absolute_cinema.domain.model.common.MovieRating
import com.example.absolute_cinema.domain.model.common.MovieTrailer
import com.example.absolute_cinema.domain.model.common.ProductionCompany
import com.example.absolute_cinema.domain.model.common.SeasonInfo
import com.example.absolute_cinema.domain.model.common.SimilarMovie
import com.example.absolute_cinema.domain.model.common.StreamingPlatform

data class MovieDetails(
    val id: Int,
    val year: Int?,
    val ageRating: Int?,
    val alternativeName: String?,
    val previewImage: String?,
    val budget: MovieBudget,
    val countries: List<String>?,
    val description: String?,
    val shortDescription: String?,
    val enName: String?,
    val name: String,
    val facts: List<MovieFact>?,
    val boxOffice: BoxOffice?,
    val genres: List<String>?,
    val isSeries: Boolean,
    val persons: List<Actor>?,
    val movieLength: Int?,
    val lists: List<String>?,
    val poster: String?,
    val productionCompanies: List<ProductionCompany>?,
    val rating: MovieRating?,
    val slogan: String?,
    val spokenLanguages: List<String>?,
    val type: String?,
    val videos: List<MovieTrailer>?,
    val similarMovies: List<SimilarMovie>?,
    val platformsAvailableOn: List<StreamingPlatform>?,
    //Для сериалов
    val seasonsInfo: List<SeasonInfo>?,
    val sequelsAndPrequels: List<MovieEpisode>?,
)
