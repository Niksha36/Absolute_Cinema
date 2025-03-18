package com.example.absolute_cinema.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.absolute_cinema.presentation.MainScreen.MainScreen
import com.example.absolute_cinema.presentation.MainScreen.MainScreenViewModel
import com.example.absolute_cinema.presentation.MovieDetailScreen.MovieDetailScreen
import com.example.absolute_cinema.presentation.MovieDetailScreen.MovieDetailViewModel

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Destination.Home
    ){

        composable<Destination.Home> {
            val mainScreenViewModel: MainScreenViewModel = hiltViewModel()
            val state by mainScreenViewModel.state.collectAsState()
            MainScreen(
                state = state,
                onEvent = mainScreenViewModel::onEvent,
                onNavigation = { movieId -> navController.navigate(Destination.MovieDetails(movieId)) }
            )
        }
        composable<Destination.MovieDetails> {
            val mainScreenViewModel: MovieDetailViewModel = hiltViewModel()
            val state = mainScreenViewModel.state
            MovieDetailScreen(state)
        }
        composable<Destination.Search> {
            //???
        }
        composable<Destination.Favorite> {
            //???
        }
    }
}