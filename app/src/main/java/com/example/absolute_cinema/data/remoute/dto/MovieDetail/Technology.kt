package com.example.absolute_cinema.data.remoute.dto.MovieDetail
import kotlinx.serialization.Serializable

@Serializable
data class Technology(
    val has3D: Boolean,
    val hasImax: Boolean
)