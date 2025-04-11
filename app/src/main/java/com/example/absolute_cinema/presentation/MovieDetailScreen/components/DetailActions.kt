package com.example.absolute_cinema.presentation.MovieDetailScreen.components

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.absolute_cinema.R
import com.example.absolute_cinema.presentation.MovieDetailScreen.MovieDetailScreenState

@Composable
fun DetailActions(
    saveAction: () -> Unit,
    deleteAction: () -> Unit,
    shareLink: String,
    state: MovieDetailScreenState
) {
    val saveIconColor = if(state.isMovieSaved) Color.Companion.Red else Color.White
    val context = LocalContext.current
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.clickable {
                if (state.isMovieSaved) deleteAction() else saveAction()
            }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Star Icon", tint = saveIconColor)
            Text(stringResource(R.string.will_watch_later_action))
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.clickable {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, shareLink)
                }
                context.startActivity(Intent.createChooser(shareIntent, "Поделиться через"))
            }) {
            Icon(Icons.Filled.Share, contentDescription = "Star Icon")
            Text(stringResource(R.string.share_action))
        }
    }
}