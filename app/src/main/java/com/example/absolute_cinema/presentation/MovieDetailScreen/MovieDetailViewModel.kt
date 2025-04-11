package com.example.absolute_cinema.presentation.MovieDetailScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.absolute_cinema.domain.use_cases.GetMovieDetailsUseCase
import com.example.absolute_cinema.domain.use_cases.MovieCrudUseCase
import com.example.absolute_cinema.presentation.navigation.Destination
import com.example.absolute_cinema.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    val movieDetailsUseCase: GetMovieDetailsUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val movieCrudUseCase: MovieCrudUseCase
) : ViewModel() {
    var state by mutableStateOf(MovieDetailScreenState())
        private set
    init {
        viewModelScope.launch {
            val id = savedStateHandle.toRoute<Destination.MovieDetails>().movieId
            Log.d("MovieDetailViewModel", "Movie id: $id")
            state = state.copy(isLoadingDetails = true)
            val detailsResponse = async {
                movieDetailsUseCase(
                    id = id,
                    page = 1,
                    limit = 250
                )
            }


            when (val response = detailsResponse.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        movieDetails = response.data,
                        isLoadingDetails = false
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        error = response.message,
                        isLoadingDetails = false
                    )
                }

                else -> Unit
            }
            movieCrudUseCase.isMovieSaved(id)
                .collectLatest { isSaved ->
                    state = state.copy(isMovieSaved = isSaved)
                }

        }

    }

    fun onEvent(event: MovieDetailsEvent) {
        when (event) {
            is MovieDetailsEvent.deleteMovie -> viewModelScope.launch {
                movieCrudUseCase.deleteMovie(event.movieId)
            }

            is MovieDetailsEvent.saveMovie -> viewModelScope.launch {
                movieCrudUseCase.saveMovie(event.movie, event.comments)
            }

            MovieDetailsEvent.hideExpandedContent -> state = state.copy(showExpandedContent = null)

            is MovieDetailsEvent.showExpandedContent -> state =
                state.copy(showExpandedContent = event.contentType)
        }
    }
}