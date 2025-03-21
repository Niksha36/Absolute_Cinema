package com.example.absolute_cinema.util

import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
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

    fun avgRating(rating1: Double? = null, rating2: Double? = null): Double {
        val r1 = rating1 ?: 0.0
        val r2 = rating2 ?: 0.0
        val average = if (r1 == 0.0) r2 else if (r2 == 0.0) r1 else ((r1 + r2) / 2.0)
        return round(average * 10) / 10.0
    }

    fun formatMoney(value: Int): String {
        return when {
            value > 1_000_000 -> "${value / 1_000_000} млн"
            value > 1_000 -> "${value / 1_000} тыс"
            else -> value.toString()
        }
    }

    fun convertDateFormat(inputDate: String): String {
        // Remove seconds and optional milliseconds
        val cleanedDate = inputDate.replace(Regex(":[0-9]{2}(\\.[0-9]+)?"), "") // Removes :ss and .SSS if present

        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HHX", Locale.getDefault()) // Correctly handles timezone
        inputFormat.timeZone = TimeZone.getTimeZone("UTC") // Ensure consistent parsing

        val outputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        return try {
            val date: Date = inputFormat.parse(cleanedDate) ?: return ""
            outputFormat.format(date)
        } catch (e: Exception) {
            "Invalid Date"
        }
    }
    fun extractVideoId(url: String): String {
        return url.substringAfterLast("/").substringBefore("?")
    }

}