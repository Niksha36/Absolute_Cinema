package com.example.absolute_cinema.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "movie_streaming_platform_url",
    foreignKeys = [
        ForeignKey(
            entity = StreamingPlatformEntity::class,
            parentColumns = ["id"],
            childColumns = ["platformId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        )],
    indices = [Index(value = ["url"], unique = true), Index(value = ["platformId"]), Index(value = ["movieId"])]
)
data class MovieStreamingPlatformUrlEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val movieId: Int,
    val platformId: Int,
    val url: String?
)
