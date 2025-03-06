package com.example.absolute_cinema.util

import com.example.absolute_cinema.R

enum class SortTypes(val displayNameResId: Int) {
    TOP_MOVIES(R.string.tabPopular),
    LATEST_MOVIES(R.string.tabNew),
    FOR_KIDS(R.string.tabForKids)
}