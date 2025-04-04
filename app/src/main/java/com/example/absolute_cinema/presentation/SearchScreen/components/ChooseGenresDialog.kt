package com.example.absolute_cinema.presentation.SearchScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.absolute_cinema.presentation.SearchScreen.SearchScreenState
import com.example.absolute_cinema.presentation.SearchScreen.FilterOptions
import com.example.absolute_cinema.presentation.SearchScreen.Genre

@Composable
fun ChooseGenresDialog(
    state: SearchScreenState,
    onEvent: (FilterOptions) -> Unit,
) {
    Dialog(onDismissRequest = { }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
            tonalElevation = 2.dp,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Column() {
                Text(
                    text = "Жанры",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 16.dp)
                )
                val genres = Genre.entries
                val selectedGenres = state.filterOptions.genres
                LazyColumn(Modifier.height(200.dp)) {
                    items(genres){ genre ->
                        val genreName = stringResource(genre.resId)
                        GenreItem(
                            genre = genreName,
                            isSelected = selectedGenres.contains(genreName),
                            onClick = {
                                val updatedGenres = if (selectedGenres.contains(genreName)) {
                                    selectedGenres - genreName
                                } else {
                                    selectedGenres + genreName
                                }
                                onEvent(
                                    state.filterOptions.copy(
                                        genres = updatedGenres
                                    )
                                )
                            }
                        )
                    }
                }
                Row {
                    Button(
                        onClick = {onEvent(state.filterOptions.copy(isGenresDialogOpened = false))},
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            "Выбрать"
                        )
                    }
                    Button(
                        onClick = {
                            onEvent(
                                state.filterOptions.copy(
                                    genres = emptyList(),
                                    isGenresDialogOpened = false
                                )
                            )
                        },
                        modifier = Modifier.padding(16.dp)
                    ) { Text(
                        "Отмена"
                    ) }
                }

            }

        }
    }
}
