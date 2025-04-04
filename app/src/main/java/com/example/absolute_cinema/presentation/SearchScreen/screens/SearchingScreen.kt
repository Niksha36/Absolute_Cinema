package com.example.absolute_cinema.presentation.SearchScreen.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.absolute_cinema.presentation.SearchScreen.SearchScreenEvent
import com.example.absolute_cinema.presentation.SearchScreen.SearchScreenState
import com.example.absolute_cinema.presentation.common.MoviesGrid

@Composable
fun SearchingScreen(
    onEvent: (SearchScreenEvent) -> Unit,
    navigateToDetails: (Int) -> Unit,
    navigateBack: () -> Unit,
    state: SearchScreenState
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    BackHandler {
        onEvent(SearchScreenEvent.SetStateToDefault)
        navigateBack()
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            modifier = Modifier.padding(bottom = 5.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "Back",
                modifier = Modifier
                    .clickable {
                        onEvent(SearchScreenEvent.SetStateToDefault)
                        navigateBack()
                    }
                    .size(30.dp),
            )
            BasicTextField(
                value = state.searchQuery,
                onValueChange = { query ->
                    onEvent(SearchScreenEvent.OnSearchQueryChange(query))
                },
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                cursorBrush = SolidColor(Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester).weight(1f),
                decorationBox = { innerTextField ->
                    if (state.searchQuery.isEmpty()) {
                        Text(
                            text = "Фильмы, сериалы, мультфильмы",
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    innerTextField()
                },
            )
            if(state.searchQuery.isNotEmpty()){
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Back",
                    modifier = Modifier
                        .clickable {
                            onEvent(SearchScreenEvent.OnSearchQueryChange(""))
                        }
                        .size(24.dp),
                )
            }

        }
        HorizontalDivider(
            color = Color.Gray,
            thickness = 1.dp
        )
        Box(Modifier.fillMaxSize()) {
            MoviesGrid(
                items = state.movies,
                onNavigation = navigateToDetails
            )
            if (state.isLoading) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}