package com.example.absolute_cinema.presentation.SearchScreen.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.absolute_cinema.presentation.SearchScreen.SearchScreenEvent
import com.example.absolute_cinema.presentation.SearchScreen.SearchScreenState
import com.example.absolute_cinema.presentation.SearchScreen.components.SearchOptionsPanel
import com.example.absolute_cinema.presentation.SearchScreen.components.SelectionCard
import com.example.absolute_cinema.util.MovieSelection

@Composable
fun MainSearchScreen(
    onEvent: (SearchScreenEvent) -> Unit,
    navigateToFilterScreen: () -> Unit,
    navigateToSearchingScreen: () -> Unit,
    navigateToSelectionScreen: (String) -> Unit
) {
    val selections = listOf(
        MovieSelection("top250", "250 лучших фильмов", Color(0xFF1E88E5)),
        MovieSelection("top500", "500 лучших фильмов", Color(0xFFD32F2F)),
        MovieSelection("popular-films", "Популярные фильмы", Color(0xFF388E3C)),
        MovieSelection("planned-to-watch-films", "Рейтинг ожидаемых фильмов", Color(0xFFFBC02D)),
        MovieSelection(
            "100_greatest_movies_XXI",
            "100 великих фильмов XXI века",
            Color(0xFF8E24AA)
        ),
        MovieSelection(
            "honourable_mentions_XXI",
            "100 великих фильмов XXI века: особые упоминания",
            Color(0xFF5E35B1)
        ),
        MovieSelection(
            "hearing_impairment",
            "Фильмы и сериалы с субтитрами для людей с особенностями слуха",
            Color(0xFF039BE5)
        ),
        MovieSelection(
            "audiodescription",
            "Фильмы и сериалы с тифлокомментариями",
            Color(0xFF43A047)
        ),
        MovieSelection("theme_vampire", "Фильмы про вампиров", Color(0xFFE53935)),
        MovieSelection(
            "theme_love",
            "Фильмы про любовь и страсть: список лучших романтических фильмов",
            Color(0xFFD81B60)
        ),
        MovieSelection(
            "theme_zombie",
            "Фильмы про зомби: список лучших фильмов про живых мертвецов",
            Color(0xFF8E24AA)
        ),
        MovieSelection(
            "theme_space",
            "Фильмы про космос: список лучших фильмов про космические путешествия",
            Color(0xFF3949AB)
        ),
        MovieSelection("theme_teenagers", "Фильмы про подростков", Color(0xFFFB8C00)),
        MovieSelection("theme_catastrophe", "Фильмы-катастрофы", Color(0xFF757575))
    )
    Column {
        SearchOptionsPanel(
            navigateToFilterScreen = navigateToFilterScreen,
            navigateToSearchingScreen = navigateToSearchingScreen
        )
        LazyColumn {
            items(selections) { selection ->
                SelectionCard(
                    selection = selection,
                    onClick = {
                        onEvent(SearchScreenEvent.OnSelection(selection.apiValue))
                        navigateToSelectionScreen(selection.name)
                    },
                    color = selection.color
                )
            }
        }
    }


}