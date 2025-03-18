package com.example.absolute_cinema.data.remoute.dto.MovieDetail

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val logo: Logo?,
    val name: String,
    val url: String?
)