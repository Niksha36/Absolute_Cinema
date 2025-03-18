package com.example.absolute_cinema.data.remoute.dto.MovieDetail

import kotlinx.serialization.Serializable

@Serializable
data class Fact(
    val spoiler: Boolean,
    val type: String?,
    val value: String?
)