package com.example.absolute_cinema.data.local

import androidx.room.TypeConverter
import com.example.absolute_cinema.domain.model.common.BoxOffice
import com.example.absolute_cinema.domain.model.common.MovieBudget
import com.example.absolute_cinema.domain.model.common.MovieRating
import com.example.absolute_cinema.domain.model.common.MovieTrailer
import com.example.absolute_cinema.domain.model.common.Region
import kotlinx.serialization.json.Json
import java.time.Instant

class Converters {
    //StringList
    @TypeConverter
    fun fromStringList(value: List<String>?): String? =
        value?.joinToString(separator = ",")

    @TypeConverter
    fun toStringList(value: String?): List<String>? =
        value?.split(",")?.map { it.trim() }

    //Trailer
    @TypeConverter
    fun fromMovieTrailerList(value: List<MovieTrailer>?): String? =
        value?.let { Json.encodeToString(it) }

    @TypeConverter
    fun toMovieTrailerList(value: String?): List<MovieTrailer>? =
        value?.let { Json.decodeFromString(it) }


    //Region
    @TypeConverter
    fun fromRegion(region: Region?): String? =
        region?.name

    @TypeConverter
    fun toRegion(value: String?): Region? =
        value?.let { Region.valueOf(it) }

    @TypeConverter
    fun fromInstant(value: Instant?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun toInstant(value: String?): Instant? {
        return value?.let { Instant.parse(it) }
    }
}