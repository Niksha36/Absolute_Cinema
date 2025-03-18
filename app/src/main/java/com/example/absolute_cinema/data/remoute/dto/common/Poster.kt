package com.example.absolute_cinema.data.remoute.dto.common

import kotlinx.serialization.Serializable

@Serializable
data class Poster(
    val previewUrl: String?,
    val url: String?
)