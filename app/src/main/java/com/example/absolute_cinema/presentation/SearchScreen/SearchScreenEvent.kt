package com.example.absolute_cinema.presentation.SearchScreen

sealed interface SearchScreenEvent {
    data class OnSearchQueryChange(val query: String) : SearchScreenEvent
    data class OnSelection(val selectionApi: String) : SearchScreenEvent
    object SetStateToDefault : SearchScreenEvent
    data class OnFilterOptionsChange(val filterOptions: FilterOptions) : SearchScreenEvent
    object ApplyFilter : SearchScreenEvent
    object SetFilterOptionsToDefault : SearchScreenEvent
}