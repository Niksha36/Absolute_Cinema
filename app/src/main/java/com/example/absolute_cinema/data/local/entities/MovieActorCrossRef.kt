package com.example.absolute_cinema.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index


@Entity(
    tableName = "movie_actors_cross_ref",
    primaryKeys = ["movieId", "actorId"],
    foreignKeys = [
        ForeignKey(entity = MovieEntity::class, parentColumns = ["id"], childColumns = ["movieId"], onDelete = CASCADE),
        ForeignKey(entity = ActorEntity::class, parentColumns = ["id"], childColumns = ["actorId"], onDelete = CASCADE)
    ],
    indices = [Index(value = ["actorId"]), Index(value = ["movieId"])]
)
data class MovieActorCrossRef(
    val movieId: Int,
    val actorId: Int
)