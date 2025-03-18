package com.example.absolute_cinema.presentation.MovieDetailScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.absolute_cinema.domain.use_cases.GetCommentsUseCase
import com.example.absolute_cinema.domain.use_cases.GetMovieDetailsUseCase
import com.example.absolute_cinema.presentation.navigation.Destination
import com.example.absolute_cinema.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    val movieDetailsUseCase: GetMovieDetailsUseCase,
    val commentsUseCase: GetCommentsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(MovieDetailScreenState())

    init {
        viewModelScope.launch {
            val id = savedStateHandle.toRoute<Destination.MovieDetails>().movieId
            Log.d("MovieDetailViewModel", "Movie id: $id")
            state = state.copy(isLoadingDetails = true, isLoadingComments = true)
            val commentsResponse = async {
                commentsUseCase(
                    movieId = id,
                    page = 1,
                    limit = 250
                )
            }
            val detailsResponse = async{
                movieDetailsUseCase(id)
            }
            when(val response = commentsResponse.await()){
                is Resource.Success -> {
                    state = state.copy(
                        comments = response.data ?: emptyList(),
                        isLoadingComments = false
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        error = response.message,
                        isLoadingComments = false
                    )
                }
                else -> Unit
            }

            when(val response = detailsResponse.await()){
                is Resource.Success -> {
                    state = state.copy(
                        movieDetails = response.data,
                        isLoadingComments = false
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        error = response.message,
                        isLoadingComments = false
                    )
                }
                else -> Unit
            }
        }
    }
}