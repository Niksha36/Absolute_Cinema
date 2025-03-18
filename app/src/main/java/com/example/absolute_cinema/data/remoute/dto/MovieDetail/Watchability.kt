package com.example.absolute_cinema.data.remoute.dto.MovieDetail
import kotlinx.serialization.Serializable

@Serializable
data class Watchability(
    val items: List<Item>?
)