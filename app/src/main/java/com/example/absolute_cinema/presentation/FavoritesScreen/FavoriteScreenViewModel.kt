package com.example.absolute_cinema.presentation.FavoritesScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.absolute_cinema.domain.model.MoviePoster
import com.example.absolute_cinema.domain.use_cases.MovieCrudUseCase
import com.example.absolute_cinema.util.FavoriteSortTypes
import com.example.absolute_cinema.util.Resource
import com.example.absolute_cinema.util.SortOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(
    val crudUseCase: MovieCrudUseCase
) : ViewModel() {

    private val _sortType =
        MutableStateFlow<FavoriteSortTypes>(FavoriteSortTypes.ByDateAdded(SortOrder.DESCENDING))
    private val _state = MutableStateFlow(FavoriteScreenState())
    val state = combine(
        crudUseCase.getMoviePosters(),
        _sortType,
        _state
    ) { resource, sortType, prevState ->
        when (resource) {
            is Resource.Loading -> prevState.copy(isLoading = true)
            is Resource.Success -> {
                val sortedMovies = sortMovies(resource.data ?: emptyList(), sortType)
                prevState.copy(isLoading = false, movies = sortedMovies)
            }
            is Resource.Error -> prevState.copy(
                isLoading = false,
                movies = emptyList(),
                error = resource.message.orEmpty()
            )
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        FavoriteScreenState()
    )


    private fun sortMovies(
        movies: List<MoviePoster>,
        sortType: FavoriteSortTypes
    ): List<MoviePoster> {
        return when (sortType) {
            is FavoriteSortTypes.ByYear -> sortByOrder(movies, sortType.order) { it.year ?: 0 }
            is FavoriteSortTypes.ByRating -> sortByOrder(movies, sortType.order) { it.absoluteCinemaRating ?: 0.0 }
            is FavoriteSortTypes.ByName -> sortByOrder(movies, sortType.order) { it.name ?: "" }
            is FavoriteSortTypes.ByDateAdded -> sortByOrder(
                movies,
                sortType.order
            ) { it.timestamp!! }
        }
    }

    private fun <T : Comparable<T>> sortByOrder(
        movies: List<MoviePoster>,
        order: SortOrder,
        selector: (MoviePoster) -> T
    ): List<MoviePoster> {
        return when (order) {
            SortOrder.ASCENDING -> movies.sortedBy(selector)
            SortOrder.DESCENDING -> movies.sortedByDescending(selector)
        }
    }



    private fun changeSortType() {
        _sortType.value = _state.value.dialogSortType
        toggleDialog()
    }
    private fun toggleOrderInDialog() {
        _state.update { state ->
            val newOrder = if (state.dialogSortOrder == SortOrder.DESCENDING) {
                SortOrder.ASCENDING
            } else {
                SortOrder.DESCENDING
            }
            state.copy(
                dialogSortOrder = newOrder,
                dialogSortType = when (state.dialogSortType) {
                    is FavoriteSortTypes.ByYear -> FavoriteSortTypes.ByYear(newOrder)
                    is FavoriteSortTypes.ByRating -> FavoriteSortTypes.ByRating(newOrder)
                    is FavoriteSortTypes.ByName -> FavoriteSortTypes.ByName(newOrder)
                    is FavoriteSortTypes.ByDateAdded -> FavoriteSortTypes.ByDateAdded(newOrder)
                }
            )

        }
        Log.d("TAG", "toggleOrderInDialog: ${_state.value.dialogSortOrder}")
    }
    private fun changeSortTypeInDialog(newSortType: FavoriteSortTypes) {
        _state.update { state ->
            state.copy(
                dialogSortType = newSortType
            )
        }
    }
    private fun cancelChangingSortType() {
        _state.update { state ->
            state.copy(
                dialogSortType = state.sortType,
                dialogSortOrder = state.sortType.order
            )
        }
    }
    private fun toggleDialog() {
        _state.update { state ->
            state.copy(
                showDialog = !state.showDialog
            )
        }
    }


    fun onEvent(event: FavoriteScreenEvent) {
        when (event) {
            is FavoriteScreenEvent.changeSortType -> changeSortType()
            is FavoriteScreenEvent.toggleOrderInDialog -> toggleOrderInDialog()
            is FavoriteScreenEvent.changeSortTypeInDialog -> changeSortTypeInDialog(event.sortType)
            is FavoriteScreenEvent.cancelChangingSortType -> cancelChangingSortType()
            is FavoriteScreenEvent.toggleDialog -> toggleDialog()
        }
    }
}