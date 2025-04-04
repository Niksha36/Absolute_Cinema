package com.example.absolute_cinema.presentation.SearchScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.absolute_cinema.domain.use_cases.GetMoviesByFilterUseCase
import com.example.absolute_cinema.domain.use_cases.GetMoviesByName
import com.example.absolute_cinema.domain.use_cases.GetMoviesBySelectionUseCase
import com.example.absolute_cinema.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    val getMoviesByName: GetMoviesByName,
    val getMoviesBySelectionUseCase: GetMoviesBySelectionUseCase,
    val getMoviesByFilterUseCase: GetMoviesByFilterUseCase
) : ViewModel() {
    var state by mutableStateOf(SearchScreenState())
    private var searchJob: Job? = null

    fun onEvent(event: SearchScreenEvent) {
        when (event) {
            is SearchScreenEvent.OnSearchQueryChange -> {
                state = state.copy(
                    searchQuery = event.query
                )
                if (event.query.isNotEmpty()) {
                    searchMovies(
                        page = 1,
                        limit = 250,
                        query = state.searchQuery.lowercase()
                    )
                } else {
                    searchJob?.cancel()
                    state = state.copy(
                        movies = emptyList(),
                        isLoading = false
                    )
                }
            }

            is SearchScreenEvent.OnSelection -> getMoviesBySelection(event.selectionApi, 1, 250)
            SearchScreenEvent.SetStateToDefault -> state = SearchScreenState(filterOptions = state.filterOptions)
            is SearchScreenEvent.OnFilterOptionsChange -> {
                state = state.copy(
                    filterOptions = event.filterOptions
                )
            }

            SearchScreenEvent.ApplyFilter -> {
                state.filterOptions.apply {
                    getMoviesByFilter(
                        page = 1,
                        limit = 250,
                        sortField = when (sortBy) {
                            SortBy.RATING -> "rating.kp"
                            SortBy.POPULARITY -> "votes.kp"
                            SortBy.DATE -> "year"
                        },
                        genres = genres.ifEmpty { null },
                        year = yearRange?.let { range ->
                            if (range.from == range.to) "${range.from}"
                            else "${range.from}-${range.to}"
                        } ,
                        rating = "${rating.first}-${rating.last}",
                        isSeries = if (type == TypeFilter.ALL) null else type == TypeFilter.SERIES
                    )
                }
            }

            SearchScreenEvent.SetFilterOptionsToDefault -> state = state.copy(
                filterOptions = FilterOptions()
            )
        }
    }

    private fun getMoviesByFilter(
        page: Int,
        limit: Int,
        sortField: String,
        genres: List<String>?,
        year: String?,
        rating: String,
        isSeries: Boolean?
    ) {
        viewModelScope.launch {
            getMoviesByFilterUseCase(
                page = page,
                limit = limit,
                sortField = sortField,
                genres = genres,
                year = year,
                rating = rating,
                isSeries = isSeries
            ).collect { result ->
                when (result) {
                    is Resource.Loading -> state = state.copy(isLoading = true)
                    is Resource.Success -> state = state.copy(
                        movies = result.data ?: emptyList(),
                        isLoading = false
                    )

                    is Resource.Error -> state = state.copy(
                        error = result.message.toString(),
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun getMoviesBySelection(
        selectionApi: String,
        page: Int,
        limit: Int
    ) {
        viewModelScope.launch {
            getMoviesBySelectionUseCase(
                page = page,
                limit = limit,
                selection = selectionApi
            ).collect { result ->
                when (result) {
                    is Resource.Loading -> state = state.copy(isLoading = true)
                    is Resource.Success -> state = state.copy(
                        movies = result.data ?: emptyList(),
                        isLoading = false
                    )

                    is Resource.Error -> state = state.copy(
                        error = result.message.toString(),
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun searchMovies(
        query: String,
        page: Int,
        limit: Int
    ) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(1000L)
            getMoviesByName(
                name = query,
                page = page,
                limit = limit
            ).collect { result ->
                when (result) {
                    is Resource.Loading -> state = state.copy(isLoading = true)
                    is Resource.Success -> state = state.copy(
                        movies = result.data ?: emptyList(),
                        isLoading = false
                    )

                    is Resource.Error -> state = state.copy(
                        error = result.message.toString(),
                        isLoading = false
                    )
                }
            }
        }
    }
}