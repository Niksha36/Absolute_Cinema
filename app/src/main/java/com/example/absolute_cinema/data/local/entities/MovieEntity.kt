package com.example.absolute_cinema.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.absolute_cinema.domain.model.common.MovieTrailer
import java.sql.Timestamp
import java.time.Instant

@Entity(tableName = "movies")
    data class MovieEntity(
    @PrimaryKey val id: Int,
    val timestamp: Instant,
    val year: Int?,
    val ageRating: Int?,
    val alternativeName: String?,
    val previewImage: String?,
    val countries: List<String>?,
    val description: String?,
    val shortDescription: String?,
    val enName: String?,
    val name: String,
    val movieLength: Int?,
    val lists: List<String>?,
    val poster: String?,
    val genres: List<String>?,
    val isSeries: Boolean,
    val spokenLanguages: List<String>?,
    val trailer: List<MovieTrailer>?,
    val slogan: String?,
    val type: String?,
    )
