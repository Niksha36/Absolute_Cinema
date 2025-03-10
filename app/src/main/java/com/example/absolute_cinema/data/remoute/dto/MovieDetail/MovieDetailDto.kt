package com.example.absolute_cinema.data.remoute.dto.MovieDetail

import com.example.absolute_cinema.data.remoute.dto.common.Genre
import com.example.absolute_cinema.data.remoute.dto.common.Poster
import com.example.absolute_cinema.data.remoute.dto.common.Rating

data class MovieDetailDto(
    val ageRating: Int?,
    val alternativeName: String?,
    val audience: List<Audience>?,
    val backdrop: Backdrop?,
    val budget: Budget,
    val countries: List<Country>?,
    val description: String?,
    val distributors: Distributors,
    val enName: String?,
    val externalId: ExternalId?,
    val facts: List<Fact>?,
    val fees: Fees?,
    val genres: List<Genre>?,
    val id: Int,
    val isSeries: Boolean,
    val isTmdbChecked: Boolean,
    val lists: List<String>?,
    val movieLength: Double?,
    val name: String?,
    val persons: List<Person>?,
    val poster: Poster?,
    val productionCompanies: List<ProductionCompany>?,
    val rating: Rating?,
    val ratingMpaa: String,
    val seasonsInfo: List<SeasonsInfo>?,
    val sequelsAndPrequels: List<SequelsAndPrequel>?,
    val shortDescription: String?,
    val similarMovies: List<SimilarMovy>?,
    val slogan: String?,
    val spokenLanguages: List<SpokenLanguage>?,
    val technology: Technology?,
    val ticketsOnSale: Boolean,
    val top10: Int?,
    val top250: Int?,
    val type: String?,
    val typeNumber: Int?,
    val updatedAt: String?,
    val videos: Videos?,
    val votes: Votes?,
    val watchability: Watchability?,
    val year: Int?
)