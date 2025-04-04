package com.example.absolute_cinema.presentation.SearchScreen

import com.example.absolute_cinema.domain.model.MoviePoster

data class SearchScreenState(
    val isLoading: Boolean = false,
    val movies: List<MoviePoster> = emptyList(),
    val error: String = "",
    val page: Int = 1,
    val limit: Int = 10,
    val searchQuery: String = "",
    val filterOptions: FilterOptions = FilterOptions()
)
