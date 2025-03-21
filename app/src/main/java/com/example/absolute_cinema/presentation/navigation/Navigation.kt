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
import com.example.absolute_cinema.domain.model.MovieComment
import com.example.absolute_cinema.presentation.MainScreen.MainScreen
import com.example.absolute_cinema.presentation.MainScreen.MainScreenViewModel
import com.example.absolute_cinema.presentation.MovieDetailScreen.ExpandedListScreen
import com.example.absolute_cinema.presentation.MovieDetailScreen.MovieDetailScreen
import com.example.absolute_cinema.presentation.MovieDetailScreen.MovieDetailViewModel
import com.example.absolute_cinema.presentation.MovieDetailScreen.components.comment.FullScreenComment
import kotlinx.serialization.decodeFromString
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
                goBack = mainScreenViewModel::hideExpandedContent,
                navigateToMovieComment = { movie -> navController.navigate(Destination.Comment(movie)) }
            )
        }
        composable<Destination.Comment> {backStackEntry ->
            val commentJson = backStackEntry.toRoute<Destination.Comment>().commentJson
            val comment = Json.decodeFromString<MovieComment>(commentJson)
            FullScreenComment(comment = comment, {navController.popBackStack()} )
        }
        composable<Destination.Search> {
            //???
        }
        composable<Destination.Favorite> {
            //???
        }
    }
}