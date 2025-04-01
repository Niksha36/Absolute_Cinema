package com.example.absolute_cinema.domain.model.common

import kotlinx.serialization.Serializable

@Serializable
data class MovieTrailer(
    val site: String?,
    val url: String?,
    val name: String?
)
