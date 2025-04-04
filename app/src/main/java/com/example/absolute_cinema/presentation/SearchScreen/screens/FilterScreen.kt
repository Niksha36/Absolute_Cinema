package com.example.absolute_cinema.presentation.SearchScreen.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.absolute_cinema.presentation.SearchScreen.SearchScreenEvent
import com.example.absolute_cinema.presentation.SearchScreen.SearchScreenState
import com.example.absolute_cinema.presentation.SearchScreen.components.ChooseGenresDialog
import com.example.absolute_cinema.presentation.SearchScreen.components.YearRangePickerDialog
import com.example.absolute_cinema.presentation.SearchScreen.SortBy
import com.example.absolute_cinema.presentation.SearchScreen.TypeFilter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(
    onEvent: (SearchScreenEvent) -> Unit,
    navigateBack: () -> Unit,
    state: SearchScreenState,
    navigateToSelection: (String) -> Unit,
) {
    BackHandler {
        onEvent(SearchScreenEvent.SetFilterOptionsToDefault)
        navigateBack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Фильтрация") },
                navigationIcon = {
                    IconButton(onClick = {
                        onEvent(SearchScreenEvent.SetFilterOptionsToDefault)
                        navigateBack()
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 8.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                "Показывать",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            val movieTypeItems = TypeFilter.entries
            val selectedTab = movieTypeItems.indexOf(state.filterOptions.type)
            TabRow(
                selectedTabIndex = selectedTab,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = MaterialTheme.colorScheme.secondaryContainer
                    )
                }
            ) {
                movieTypeItems.forEachIndexed { index, categoryObj ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = {
                            onEvent(
                                SearchScreenEvent.OnFilterOptionsChange(
                                    state.filterOptions.copy(type = categoryObj)
                                )
                            )
                        },
                        text = { Text(stringResource(categoryObj.resId), fontSize = 13.sp) }
                    )
                }
            }

            //Жанры
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clickable {
                        onEvent(
                            SearchScreenEvent.OnFilterOptionsChange(
                                state.filterOptions.copy(isGenresDialogOpened = true)
                            )
                        )
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Жанры",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(end = 10.dp)
                )
                val genreItems = state.filterOptions.genres
                Row {
                    if (genreItems.isEmpty()) {
                        Text(
                            text = "любой",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )
                    } else {
                        val selectedGenres = genreItems.joinToString(separator = ", ")
                        Text(
                            selectedGenres,
                            modifier = Modifier.weight(1f),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }
            //Год
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clickable {
                        onEvent(
                            SearchScreenEvent.OnFilterOptionsChange(
                                state.filterOptions.copy(
                                    isYearRangeDialogOpened = true
                                )
                            )
                        )
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Год",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                val year = state.filterOptions.yearRange
                year?.let {
                    if(it.from == it.to) {
                        Text(
                            text = "${it.from}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )
                    } else {
                        Text(
                            text = "${it.from} - ${it.to}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                } ?: Text(
                    text = "любой",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
            }

            //Рейтинг
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Рейтинг",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Text(
                    text = "${state.filterOptions.rating.first} - ${state.filterOptions.rating.last}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
            }

            val currentRange = state.filterOptions.rating
            var sliderPosition by remember {
                mutableStateOf(currentRange.first.toFloat()..currentRange.last.toFloat())
            }
            RangeSlider(
                value = sliderPosition,
                valueRange = 0f..10f,
                onValueChange = { range ->
                    sliderPosition = range
                },
                onValueChangeFinished = {
                    onEvent(
                        SearchScreenEvent.OnFilterOptionsChange(
                            state.filterOptions.copy(
                                rating = sliderPosition.start.toInt()..sliderPosition.endInclusive.toInt()
                            )
                        )
                    )
                },
                modifier = Modifier.padding(horizontal = 15.dp),
            )


            //Сортировать по
            Text(
                text = "Сортировать по",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )

            val sortByItems = SortBy.entries
            val selectedSortTab = sortByItems.indexOf(state.filterOptions.sortBy)
            TabRow(
                selectedTabIndex = selectedSortTab,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedSortTab]),
                        color = MaterialTheme.colorScheme.secondaryContainer
                    )
                }
            ) {
                sortByItems.forEachIndexed { index, categoryObj ->
                    Tab(
                        selected = selectedSortTab == index,
                        onClick = {
                            onEvent(
                                SearchScreenEvent.OnFilterOptionsChange(
                                    state.filterOptions.copy(
                                        sortBy = categoryObj
                                    )
                                )
                            )
                        },
                        text = { Text(stringResource(categoryObj.resId), fontSize = 13.sp) }
                    )
                }
            }
            //Кнопка Применить
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    onEvent(SearchScreenEvent.ApplyFilter)
                    navigateToSelection("Результаты")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    text = "Применить",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            if (state.filterOptions.isGenresDialogOpened) {
                ChooseGenresDialog(
                    state = state,
                    onEvent = { value -> onEvent(SearchScreenEvent.OnFilterOptionsChange(value)) },
                )
            }
            if (state.filterOptions.isYearRangeDialogOpened){
                YearRangePickerDialog(
                    onEvent = onEvent,
                    state = state
                )
            }
        }
    }
}