package com.example.absolute_cinema.util

import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.round

object UtilFunctions {
    fun getYearInterval(): String {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val calendar = Calendar.getInstance()
        val currentDate = calendar.time
        val currentDateString = dateFormat.format(currentDate)

        calendar.add(Calendar.YEAR, -1)
        val previousYearDate = calendar.time
        val previousYearDateString = dateFormat.format(previousYearDate)

        return "$previousYearDateString-$currentDateString"
    }

    fun ratingColor(rating: Double): Color {
        return when {
            rating > 7.4 -> Color.Green
            rating <= 7.4 -> Color.Gray
            else -> Color.Red
        }
    }

    fun avgRating(rating1: Double?, rating2: Double?): Double {
        val r1 = rating1 ?: 0.0
        val r2 = rating2 ?: 0.0
        val average = if (r1 == 0.0) r2 else if (r2 == 0.0) r1 else ((r1 + r2) / 2.0)
        return round(average * 10) / 10.0
    }
}