package com.example.absolute_cinema.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class MovieTrailerEntity(
    @PrimaryKey(autoGenerate = true) val trailerId: Int = 0,
    val movieId: Int,
    val site: String?,
    val url: String?
)
