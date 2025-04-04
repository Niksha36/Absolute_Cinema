package com.example.absolute_cinema.presentation.SearchScreen.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.absolute_cinema.presentation.SearchScreen.SearchScreenEvent
import com.example.absolute_cinema.presentation.SearchScreen.SearchScreenState
import com.example.absolute_cinema.presentation.common.MoviesGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionScreen(
    navigateBack: () -> Unit,
    navigateToMovieDetails: (Int) -> Unit,
    purpose: String,
    state: SearchScreenState,
    onEvent: (SearchScreenEvent) -> Unit,
) {
    BackHandler {
        onEvent(SearchScreenEvent.SetStateToDefault)
        navigateBack()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = purpose) },
                navigationIcon = {
                    IconButton(onClick = {
                        onEvent(SearchScreenEvent.SetStateToDefault)
                        navigateBack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Box(Modifier
            .fillMaxSize()
            .padding(it)) {
            val movies = state.movies
            MoviesGrid(movies) { movieId -> navigateToMovieDetails(movieId) }
            if(state.isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}