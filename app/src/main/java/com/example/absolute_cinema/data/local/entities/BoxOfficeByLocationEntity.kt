package com.example.absolute_cinema.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.absolute_cinema.domain.model.common.Region

@Entity(
    tableName = "box_office",
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = ["id"],
        childColumns = ["movieId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("movieId")]
)
data class BoxOfficeByLocationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val movieId: Int,
    val region: Region,
    val currency: String?,
    val value: Int?
)