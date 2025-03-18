package com.example.absolute_cinema.data.remoute.dto.MovieDetail
import kotlinx.serialization.Serializable

@Serializable
data class Trailer(
    val name: String?,
    val site: String?,
    val type: String?,
    val url: String?
)