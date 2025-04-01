package com.example.absolute_cinema.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index

@Entity(
    tableName = "movie_episode_cross_ref",
    primaryKeys = ["movieId", "episodeId"],
    foreignKeys = [
        ForeignKey(entity = MovieEntity::class, parentColumns = ["id"], childColumns = ["movieId"], onDelete = CASCADE),
        ForeignKey(entity = MovieEpisodeEntity::class, parentColumns = ["id"], childColumns = ["episodeId"], onDelete = CASCADE),
    ],
    indices = [Index(value = ["episodeId"]), Index(value = ["movieId"])]
)
data class MovieEpisodeCrossRef(
    val movieId: Int,
    val episodeId: Int
)
