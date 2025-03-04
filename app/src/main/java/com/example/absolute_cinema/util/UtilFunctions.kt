package com.example.absolute_cinema.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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
}