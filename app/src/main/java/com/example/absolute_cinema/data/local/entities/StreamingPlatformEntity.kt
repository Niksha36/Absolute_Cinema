package com.example.absolute_cinema.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "streaming_platform")
data class StreamingPlatformEntity(
    @PrimaryKey val id: Int = 0,
    val logo: String?,
    val name: String?,
)
