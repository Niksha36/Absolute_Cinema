package com.example.absolute_cinema.presentation.MainScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.absolute_cinema.domain.use_cases.GetForKidsUseCase
import com.example.absolute_cinema.domain.use_cases.GetLatestMoviesUseCase
import com.example.absolute_cinema.domain.use_cases.GetTopMoviesUseCase
import com.example.absolute_cinema.util.Resource
import com.example.absolute_cinema.util.SortTypes
import com.example.absolute_cinema.util.UtilFunctions.getYearInterval
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

//page and data ???
@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getTopMoviesUseCase: GetTopMoviesUseCase,
    private val getForKidsUseCase: GetForKidsUseCase,
    private val getLatestMoviesUseCase: GetLatestMoviesUseCase
) : ViewModel() {
    private val limit = 250
    private val _sortType = MutableStateFlow(SortTypes.TOP_MOVIES)
    private val _state = MutableStateFlow(MainScreenState())
    private val _page = MutableStateFlow(1)
    private val _retryTrigger = MutableStateFlow(0)

    val moviesResponse = combine(_sortType, _page, _retryTrigger) { sortType, page, _ ->
        sortType to page
    }.flatMapLatest { (sortType, page) ->
        when (sortType) {
            SortTypes.TOP_MOVIES -> getTopMoviesUseCase(
                page = page,
                limit = limit
            )

            SortTypes.LATEST_MOVIES -> {
                getLatestMoviesUseCase(
                    page = page,
                    limit = limit,
                    date = getYearInterval()
                )
            }

            SortTypes.FOR_KIDS -> getForKidsUseCase(
                page = page,
                limit = limit
            )
        }
    }

    val state = combine(_state, moviesResponse, _sortType) { state, response, sortType ->
        when(response){
            is Resource.Error -> {
                state.copy(
                    sortType = sortType,
                    error = response.message.toString(),
                    isLoading = false
                )
            }
            is Resource.Loading -> state.copy(
                sortType = sortType,
                isLoading = true
            )
            is Resource.Success -> state.copy(
                sortType = sortType,
                movies = response.data ?: emptyList(),
                isLoading = false
            )
        }

    }.stateIn(viewModelScope, SharingStarted.Eagerly, MainScreenState())
    //.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MainScreenState())
    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.ChangingCategory -> {
                val category = event.category
                if (_sortType.value != category) {
                    _state.update { MainScreenState() }
                    _sortType.value = category
                }

            }

            is MainScreenEvent.ChangingPage -> {
                _page.update { event.page }
            }

            MainScreenEvent.Retry -> {
                _retryTrigger.value += 1
            }
        }
    }
    init {
        Log.d("MainScreenViewModel", "ViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MainScreenViewModel", "ViewModel cleared")
    }
}