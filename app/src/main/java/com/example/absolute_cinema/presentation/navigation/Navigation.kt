package com.example.absolute_cinema.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.absolute_cinema.presentation.MainScreen.MainScreen
import com.example.absolute_cinema.presentation.MainScreen.MainScreenViewModel
import com.example.absolute_cinema.presentation.MovieDetailScreen.ExpandedListScreen
import com.example.absolute_cinema.presentation.MovieDetailScreen.MovieDetailScreen
import com.example.absolute_cinema.presentation.MovieDetailScreen.MovieDetailViewModel
import kotlinx.serialization.json.Json

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Destination.Home
    ) {

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
            MovieDetailScreen(
                state,
                onNavigateToMovieDetails = { id ->
                    navController.navigate(
                        Destination.MovieDetails(id)
                    )
                },
                toggleShowExpandedContent = mainScreenViewModel::showExpandedContent,
                goBack = mainScreenViewModel::hideExpandedContent
            )
        }
        composable<Destination.Search> {
            //???
        }
        composable<Destination.Favorite> {
            //???
        }
    }
}