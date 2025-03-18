package com.example.absolute_cinema.presentation.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ErrorDialog(
    message: String,
    onRetry: () -> Unit
) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Ошибка") },
            text = { Text(text = message) },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    onRetry()
                }) {
                    Text("Перезагрузить")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Закрыть")
                }
            }
        )
    }
}