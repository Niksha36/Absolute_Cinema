package com.example.absolute_cinema.presentation.MainScreen

import com.example.absolute_cinema.domain.model.MoviePoster
import com.example.absolute_cinema.util.SortTypes

data class MainScreenState(
    val movies: List<MoviePoster> = emptyList(),
    val sortType: SortTypes = SortTypes.TOP_MOVIES,
    val page: Int = 1,
    val isLoading: Boolean = false,
    val error: String = ""
)