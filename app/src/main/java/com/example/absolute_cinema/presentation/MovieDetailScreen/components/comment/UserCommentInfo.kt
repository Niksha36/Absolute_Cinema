package com.example.absolute_cinema.presentation.MovieDetailScreen.components.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.absolute_cinema.domain.model.MovieComment

@Composable
fun UserCommentInfo(
    comment: MovieComment,
    textThemeStyles: Typography,
    bottomPadding: Int = 5
){
    Row(
        horizontalArrangement = spacedBy(5.dp),
        modifier = Modifier.height(IntrinsicSize.Min).padding(bottom = bottomPadding.dp)
    ) {
        val boxIndicatorColor = if (comment.type == "Позитивный") {
            Color.Green
        } else if (comment.type == "Нейтральный") {
            Color.Yellow
        } else {
            Color.Red
        }

        Box(
            modifier = Modifier
                .width(5.dp)
                .fillMaxHeight()
                .background(boxIndicatorColor)
        )
        Column {
            Text(
                comment.author ?: "-",
                style = textThemeStyles.titleMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
            )
            Text(comment.date, style = textThemeStyles.bodySmall)
        }
    }
}