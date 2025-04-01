package com.example.absolute_cinema.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actors")
data class ActorEntity(
    @PrimaryKey
    val id: Int = 0,
    val description: String?,
    val enName: String?,
    val enProfession: String?,
    val name: String?,
    val photo: String?,
    val profession: String?
)
