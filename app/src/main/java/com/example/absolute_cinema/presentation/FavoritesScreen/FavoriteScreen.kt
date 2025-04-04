package com.example.absolute_cinema.presentation.FavoritesScreen

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.absolute_cinema.R
import com.example.absolute_cinema.presentation.FavoritesScreen.components.SortingDialog
import com.example.absolute_cinema.presentation.common.MovieCard
import com.example.absolute_cinema.presentation.common.MoviesGrid
import com.example.absolute_cinema.util.FavoriteSortTypes
import com.example.compose.outlineDark

@Composable
fun FavoriteScreen(
    state: FavoriteScreenState,
    onEvent: (FavoriteScreenEvent) -> Unit,
    onNavigation: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column() {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onEvent(FavoriteScreenEvent.toggleDialog)
                    }
                    .padding(8.dp)
            ) {
                Text(
                    stringResource(R.string.favorite_screen_sorting_text),
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(state.sortType.nameResId),
                        style = MaterialTheme.typography.titleSmall,
                        color = outlineDark
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowUp,
                        contentDescription = null,
                        modifier = Modifier.graphicsLayer(rotationZ = 90f),
                        tint = outlineDark
                    )
                }
            }
            MoviesGrid(state.movies) { movieId ->
                onNavigation(movieId)
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        if (state.showDialog) {
            SortingDialog(
                state = state,
                onDismissRequest = { onEvent(FavoriteScreenEvent.toggleDialog) },
                onSortTypeSelected = { onEvent(FavoriteScreenEvent.changeSortTypeInDialog(it)) },
                applyChanges = { onEvent(FavoriteScreenEvent.changeSortType) },
                toggleSortOrder = { onEvent(FavoriteScreenEvent.toggleOrderInDialog) }
            )
        }

    }
}
