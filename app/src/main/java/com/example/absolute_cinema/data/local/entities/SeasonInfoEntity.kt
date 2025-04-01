package com.example.absolute_cinema.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "seasons",
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = ["id"],
        childColumns = ["movieId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("movieId")]
)
data class SeasonInfoEntity(
    @PrimaryKey(autoGenerate = true) val seasonId: Int = 0,
    val movieId: Int,
    val episodesCount: Int?,
    val number: Int?
)
