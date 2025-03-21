package com.example.absolute_cinema.presentation.MovieDetailScreen

import com.example.absolute_cinema.domain.model.MovieComment
import com.example.absolute_cinema.domain.model.MovieDetails

data class MovieDetailScreenState(
    val movieDetails: MovieDetails? = null,
    val comments: List<MovieComment> = emptyList(),
    val isLoadingDetails: Boolean = false,
    val isLoadingComments: Boolean = false,
    val error: String? = null,
    val showExpandedContent: ExpandedListContentTypes? = null,
    val selectedComment: MovieComment? = null
)
