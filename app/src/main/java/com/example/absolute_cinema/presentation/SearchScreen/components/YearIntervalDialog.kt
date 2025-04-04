package com.example.absolute_cinema.presentation.SearchScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme


import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.absolute_cinema.presentation.SearchScreen.SearchScreenEvent
import com.example.absolute_cinema.presentation.SearchScreen.SearchScreenState
import com.example.absolute_cinema.presentation.SearchScreen.YearRange
import java.time.LocalDate


@Composable
fun YearRangePickerDialog(
    onEvent: (SearchScreenEvent) -> Unit,
    state: SearchScreenState,
) {
    val selectedYearRange = state.filterOptions.yearRange ?: YearRange(
        from = LocalDate.now().year,
        to = LocalDate.now().year
    )
    AlertDialog(
        onDismissRequest = {
            onEvent(
                SearchScreenEvent.OnFilterOptionsChange(
                    state.filterOptions.copy(
                        isYearRangeDialogOpened = false
                    )
                )
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Выберите диапазон лет", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "От", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        YearPicker(
                            width = 120.dp,
                            itemHeight = 40.dp,
                            textStyle = MaterialTheme.typography.titleSmall,
                            textColor = Color.Gray,
                            selectedTextColor = Color.White,
                            currentlySelected = selectedYearRange.from,
                            onYearSelected = { year ->
                                val newStartYear = year.toInt()
                                onEvent(
                                    SearchScreenEvent.OnFilterOptionsChange(
                                        state.filterOptions.copy(
                                            yearRange = YearRange(
                                                from = newStartYear,
                                                to = selectedYearRange.to
                                            )
                                        )
                                    )
                                )
                            }
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "До", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        YearPicker(
                            width = 120.dp,
                            itemHeight = 40.dp,
                            textStyle = MaterialTheme.typography.titleSmall,
                            textColor = Color.Gray,
                            currentlySelected = selectedYearRange.to,
                            selectedTextColor = Color.White,
                            onYearSelected = { year ->
                                val newEndYear = year.toInt()
                                onEvent(
                                    SearchScreenEvent.OnFilterOptionsChange(
                                        state.filterOptions.copy(
                                            yearRange = YearRange(
                                                from = selectedYearRange.from,
                                                to = newEndYear
                                            )
                                        )
                                    )
                                )
                            }
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onEvent(
                    SearchScreenEvent.OnFilterOptionsChange(
                        state.filterOptions.copy(
                            isYearRangeDialogOpened = false
                        )
                    )
                )
            }) {
                Text("Выбрать", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onEvent(
                    SearchScreenEvent.OnFilterOptionsChange(
                        state.filterOptions.copy(
                            isYearRangeDialogOpened = false,
                            yearRange = null
                        )
                    )
                )
            }) {
                Text("Сбросить", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    )
}