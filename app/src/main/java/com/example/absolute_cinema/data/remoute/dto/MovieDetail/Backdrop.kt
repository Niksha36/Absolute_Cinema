package com.example.absolute_cinema.data.remoute.dto.MovieDetail

import kotlinx.serialization.Serializable

@Serializable
data class Backdrop(
    val previewUrl: String?,
    val url: String?
)