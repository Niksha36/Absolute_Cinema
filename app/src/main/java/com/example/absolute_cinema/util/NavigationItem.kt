package com.example.absolute_cinema.util

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.absolute_cinema.presentation.navigation.Destination

data class NavigationItem(
    val label: String,
    val icon: ImageVector,
    val route: Destination
)
