package com.example.absolute_cinema.data.remoute.dto.MovieDetail
import kotlinx.serialization.Serializable

@Serializable
data class SeasonsInfo(
    val episodesCount: Int?,
    val number: Int?
)