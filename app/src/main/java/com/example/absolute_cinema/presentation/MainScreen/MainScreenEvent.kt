package com.example.absolute_cinema.presentation.MainScreen

import com.example.absolute_cinema.util.SortTypes

sealed interface MainScreenEvent {
    data class ChangingCategory(val category: SortTypes): MainScreenEvent
    data class ChangingPage(val page:Int): MainScreenEvent
    object Retry: MainScreenEvent
}