package com.example.absolute_cinema.domain.model.common

data class BoxOffice(
    val russia: BoxByLocation,
    val usa: BoxByLocation,
    val world: BoxByLocation
)
