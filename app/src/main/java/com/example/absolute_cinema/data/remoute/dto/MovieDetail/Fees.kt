package com.example.absolute_cinema.data.remoute.dto.MovieDetail

import kotlinx.serialization.Serializable

@Serializable
data class Fees(
    val russia: Russia?,
    val usa: Usa?,
    val world: World?
)