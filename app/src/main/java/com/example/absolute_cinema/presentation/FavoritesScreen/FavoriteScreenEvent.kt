package com.example.absolute_cinema.presentation.FavoritesScreen

import com.example.absolute_cinema.domain.model.MoviePoster
import com.example.absolute_cinema.util.FavoriteSortTypes

sealed interface FavoriteScreenEvent {

    object changeSortType : FavoriteScreenEvent

    object toggleOrderInDialog : FavoriteScreenEvent
    object cancelChangingSortType : FavoriteScreenEvent
    class changeSortTypeInDialog(
        val sortType: FavoriteSortTypes
    ) : FavoriteScreenEvent
    object toggleDialog : FavoriteScreenEvent
}