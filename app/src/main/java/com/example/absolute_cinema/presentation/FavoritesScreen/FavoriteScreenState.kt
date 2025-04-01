package com.example.absolute_cinema.presentation.FavoritesScreen

import com.example.absolute_cinema.domain.model.MoviePoster
import com.example.absolute_cinema.util.FavoriteSortTypes
import com.example.absolute_cinema.util.SortOrder
import com.example.absolute_cinema.util.SortTypes

data class FavoriteScreenState(
    val isLoading: Boolean = false,
    val movies: List<MoviePoster> = emptyList(),
    val sortType: FavoriteSortTypes = FavoriteSortTypes.ByDateAdded(SortOrder.DESCENDING),
    val dialogSortOrder: SortOrder = SortOrder.DESCENDING,
    val dialogSortType: FavoriteSortTypes = FavoriteSortTypes.ByDateAdded(dialogSortOrder),
    val error: String = "",
    val showDialog:Boolean = false,
)
