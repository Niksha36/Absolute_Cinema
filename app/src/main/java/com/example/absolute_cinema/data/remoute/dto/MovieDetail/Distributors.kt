package com.example.absolute_cinema.data.remoute.dto.MovieDetail

import kotlinx.serialization.Serializable

@Serializable
data class Distributors(
    val distributor: String?,
    val distributorRelease: String?
)