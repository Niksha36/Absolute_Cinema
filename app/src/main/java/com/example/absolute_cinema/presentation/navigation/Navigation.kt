package com.example.absolute_cinema.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.absolute_cinema.domain.model.MovieComment
import com.example.absolute_cinema.presentation.FavoritesScreen.FavoriteScreen
import com.example.absolute_cinema.presentation.FavoritesScreen.FavoriteScreenViewModel
import com.example.absolute_cinema.presentation.MainScreen.MainScreen
import com.example.absolute_cinema.presentation.MainScreen.MainScreenViewModel
import com.example.absolute_cinema.presentation.MovieDetailScreen.MovieDetailScreen
import com.example.absolute_cinema.presentation.MovieDetailScreen.MovieDetailViewModel
import com.example.absolute_cinema.presentation.MovieDetailScreen.components.comment.FullScreenComment
import com.example.absolute_cinema.presentation.SearchScreen.SearchScreenViewModel
import com.example.absolute_cinema.presentation.SearchScreen.screens.FilterScreen
import com.example.absolute_cinema.presentation.SearchScreen.screens.MainSearchScreen
import com.example.absolute_cinema.presentation.SearchScreen.screens.SearchingScreen
import com.example.absolute_cinema.presentation.SearchScreen.screens.SelectionScreen
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
                navigateToMovieComment = { movie -> navController.navigate(Destination.Comment(movie)) },
                onEvent = mainScreenViewModel::onEvent,
            )
        }
        composable<Destination.Comment> {backStackEntry ->
            val commentJson = backStackEntry.toRoute<Destination.Comment>().commentJson
            val comment = Json.decodeFromString<MovieComment>(commentJson)
            FullScreenComment(comment = comment, {navController.popBackStack()} )
        }
        composable<Destination.Favorite> {
            val favoriteScreenViewModel:FavoriteScreenViewModel = hiltViewModel()
            val state = favoriteScreenViewModel.state
            FavoriteScreen(
                state = state,
                onEvent = favoriteScreenViewModel::onEvent,
                onNavigation = { movieId -> navController.navigate(Destination.MovieDetails(movieId)) }
            )
        }
        navigation<Destination.SearchScreens>(startDestination = Destination.Search) {
            composable<Destination.Search> {backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry<Destination.SearchScreens>()
                }
                val searchScreenViewModel: SearchScreenViewModel = hiltViewModel(parentEntry)
                MainSearchScreen(
                    onEvent = searchScreenViewModel::onEvent,
                    navigateToFilterScreen = { navController.navigate(Destination.FilterScreen) },
                    navigateToSearchingScreen = { navController.navigate(Destination.SearchingScreen) },
                    navigateToSelectionScreen = { navController.navigate(Destination.SearchSelection(it)) }
                )
            }
            composable<Destination.SearchSelection> { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry<Destination.SearchScreens>()
                }
                val searchScreenViewModel: SearchScreenViewModel = hiltViewModel(parentEntry)
                val purpose = backStackEntry.toRoute<Destination.SearchSelection>().purpose
                SelectionScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToMovieDetails = { id -> navController.navigate(Destination.MovieDetails(id)) },
                    purpose = purpose,
                    state = searchScreenViewModel.state,
                    onEvent = searchScreenViewModel::onEvent,
                )
            }

            composable<Destination.SearchingScreen> {
                val parentEntry = remember(it) {
                    navController.getBackStackEntry<Destination.SearchScreens>()
                }
                val searchScreenViewModel: SearchScreenViewModel = hiltViewModel(parentEntry)
                SearchingScreen(
                    onEvent = searchScreenViewModel::onEvent,
                    navigateToDetails = { id -> navController.navigate(Destination.MovieDetails(id)) },
                    navigateBack = { navController.popBackStack() },
                    state = searchScreenViewModel.state
                )
            }

            composable<Destination.FilterScreen> {
                val parentEntry = remember(it) {
                    navController.getBackStackEntry<Destination.SearchScreens>()
                }
                val searchScreenViewModel: SearchScreenViewModel = hiltViewModel(parentEntry)
                FilterScreen(
                    onEvent = searchScreenViewModel::onEvent,
                    navigateBack = { navController.popBackStack() },
                    state = searchScreenViewModel.state,
                    navigateToSelection = { navController.navigate(Destination.SearchSelection(it)) },
                )
            }
        }
    }
}