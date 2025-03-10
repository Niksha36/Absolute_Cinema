package com.example.absolute_cinema.data.remoute.dto.MovieDetail

import com.example.absolute_cinema.data.remoute.dto.common.Poster
import com.example.absolute_cinema.data.remoute.dto.common.Rating

data class SequelsAndPrequel(
    val alternativeName: String?,
    val enName: String?,
    val id: Int,
    val name: String?,
    val poster: Poster?,
    val rating: Rating?,
    val type: String?,
    val year: Int?
)