package com.example.absolute_cinema.presentation.MovieDetailScreen

import com.example.absolute_cinema.R

enum class ExpandedListContentTypes(val displayNameResId: Int) {
    COMMENTS(R.string.ExpandedContTypeComments),
    Facts(R.string.ExpandedContTypeFacts),
    SINGLE_COMMENT(R.string.ExpandedContTypeSingleComment)
}