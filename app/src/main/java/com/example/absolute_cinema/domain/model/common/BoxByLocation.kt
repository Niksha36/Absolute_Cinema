package com.example.absolute_cinema.domain.model.common

data class BoxByLocation(
    val region: Region,
    val currency: String?,
    val value: Int?
)
