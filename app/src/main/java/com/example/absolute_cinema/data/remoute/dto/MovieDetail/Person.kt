package com.example.absolute_cinema.data.remoute.dto.MovieDetail

import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val description: String?,
    val enName: String?,
    val enProfession: String?,
    val id: Int,
    val name: String?,
    val photo: String?,
    val profession: String?
)