package com.example.absolute_cinema.presentation.MainScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.absolute_cinema.presentation.common.ErrorDialog
import com.example.absolute_cinema.presentation.common.MovieCard
import com.example.absolute_cinema.presentation.common.MoviesGrid
import com.example.absolute_cinema.util.SortTypes
import com.example.absolute_cinema.util.UtilFunctions

@Composable
fun MainScreen(
    state: MainScreenState,
    onEvent: (MainScreenEvent) -> Unit,
    onNavigation: (Int) -> Unit
) {
    val tabItems = SortTypes.entries
    var selectedTab  = tabItems.indexOf(state.sortType)
    Scaffold(
        topBar = {
            TabRow(
                selectedTabIndex = selectedTab,
            ) {
                tabItems.forEachIndexed { index, categoryObj ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = {
                            selectedTab = index
                            onEvent(MainScreenEvent.ChangingCategory(categoryObj))
                        },
                        text = { Text(stringResource(categoryObj.displayNameResId)) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            MoviesGrid(state.movies) { movieId -> onNavigation(movieId)}

            if(state.isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            if(state.error.isNotBlank()){
                ErrorDialog(state.error, onRetry = { onEvent(MainScreenEvent.Retry) })
            }
        }
    }
}