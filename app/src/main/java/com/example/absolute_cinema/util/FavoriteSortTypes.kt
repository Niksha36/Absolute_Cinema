package com.example.absolute_cinema.util

import com.example.absolute_cinema.R

enum class SortOrder(val nameResId: Int) {
    ASCENDING(R.string.ASC_string),
    DESCENDING(R.string.DESC_string)
}

sealed class FavoriteSortTypes(val order: SortOrder = SortOrder.ASCENDING, val nameResId: Int) {
    class ByYear(order: SortOrder) : FavoriteSortTypes(order, R.string.favorite_sort_type_year)
    class ByRating(order: SortOrder) : FavoriteSortTypes(order, R.string.favorite_sort_type_rating)
    class ByName(order: SortOrder) : FavoriteSortTypes(order, R.string.favorite_sort_type_name)
    class ByDateAdded(order: SortOrder) :
        FavoriteSortTypes(order, R.string.favorite_sort_type_adding_date)
}