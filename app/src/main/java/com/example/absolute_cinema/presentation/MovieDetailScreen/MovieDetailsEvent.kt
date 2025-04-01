package com.example.absolute_cinema.presentation.MovieDetailScreen

import com.example.absolute_cinema.domain.model.MovieComment
import com.example.absolute_cinema.domain.model.MovieDetails

sealed interface MovieDetailsEvent {
    data class showExpandedContent(val contentType: ExpandedListContentTypes):MovieDetailsEvent
    object hideExpandedContent:MovieDetailsEvent
    data class saveMovie(val movie: MovieDetails, val comments: List<MovieComment>):MovieDetailsEvent
    data class deleteMovie(val movieId: Int):MovieDetailsEvent
}