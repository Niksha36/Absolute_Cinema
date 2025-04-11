package com.example.absolute_cinema.presentation.FavoritesScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.absolute_cinema.R
import com.example.absolute_cinema.presentation.FavoritesScreen.FavoriteScreenState
import com.example.absolute_cinema.util.FavoriteSortTypes

@Composable
fun SortingDialog(
    state: FavoriteScreenState,
    onDismissRequest: () -> Unit,
    toggleSortOrder: () -> Unit,
    onSortTypeSelected: (FavoriteSortTypes) -> Unit,
    applyChanges: () -> Unit,
) {
    Dialog(onDismissRequest = { }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 2.dp,
        ){

            Column(modifier = Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(Modifier.fillMaxWidth().clickable {
                    toggleSortOrder()
                }, horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Сортировка",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = stringResource(state.dialogSortOrder.nameResId),
                    )
                }
                CustomRadioButton(
                    selected = state.dialogSortType is FavoriteSortTypes.ByDateAdded,
                    onClick = { onSortTypeSelected(FavoriteSortTypes.ByDateAdded(state.dialogSortOrder)) },
                    text = stringResource(R.string.favorite_sort_type_adding_date)
                )
                CustomRadioButton(
                    selected = state.dialogSortType is FavoriteSortTypes.ByName,
                    onClick = { onSortTypeSelected(FavoriteSortTypes.ByName(state.dialogSortOrder)) },
                    text = stringResource(R.string.favorite_sort_type_name)
                )
                CustomRadioButton(
                    selected = state.dialogSortType is FavoriteSortTypes.ByRating,
                    onClick = { onSortTypeSelected(FavoriteSortTypes.ByRating(state.dialogSortOrder)) },
                    text = stringResource(R.string.favorite_sort_type_rating)
                )
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.End)) {
                    Button(
                        onClick = { onDismissRequest() },
                    ) { Text("Отмена") }
                    Button(
                        onClick = { applyChanges() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        )
                    ) {
                        Text("Ок")
                    }
                }
            }


        }

    }
}