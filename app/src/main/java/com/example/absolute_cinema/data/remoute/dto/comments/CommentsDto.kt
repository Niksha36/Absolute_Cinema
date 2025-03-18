package com.example.absolute_cinema.data.remoute.dto.comments

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentsDto(
    @SerialName("docs")
    val comments: List<Comment>
)