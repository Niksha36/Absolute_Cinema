package com.example.absolute_cinema.domain.model.common

import androidx.compose.ui.res.stringResource
import com.example.absolute_cinema.R

enum class Region(name: Int) {
    RUSSIA(R.string.location_Russia) ,
    WORLD(R.string.location_world),
    USA(R.string.location_USA)
}