package com.example.absolute_cinema.data.mapper

import com.example.absolute_cinema.data.remoute.dto.comments.CommentsDto
import com.example.absolute_cinema.domain.model.MovieComment
import com.example.absolute_cinema.util.UtilFunctions.convertDateFormat

fun CommentsDto.listOfCommentsMapper(): List<MovieComment> {
    return comments.map { comment->
        MovieComment(
            author = comment.author,
            authorId = comment.authorId,
            date = comment.date?.let { convertDateFormat(it) } ?: "-",
            id = comment.id,
            movieId = comment.movieId,
            review = comment.review,
            reviewDislikes = comment.reviewDislikes,
            reviewLikes = comment.reviewLikes,
            title = comment.title,
            type = comment.type,
            userRating = comment.userRating
        )
    }
}