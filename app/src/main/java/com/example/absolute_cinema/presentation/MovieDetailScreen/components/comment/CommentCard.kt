package com.example.absolute_cinema.presentation.MovieDetailScreen.components.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.absolute_cinema.data.remoute.dto.comments.Comment
import com.example.absolute_cinema.domain.model.MovieComment
import kotlinx.serialization.json.Json

@Composable
fun CommentCard(
    comment: MovieComment,
    modifier: Modifier,
    toggleShowExpandedContent: (String) -> Unit,

) {
    Card(Modifier.fillMaxHeight().clickable {
        val commentJson = Json.encodeToString(comment)
        toggleShowExpandedContent(commentJson)
    }) {
        Column(modifier.padding(horizontal = 10.dp, vertical = 10.dp)) {
            val textThemeStyles = MaterialTheme.typography
            UserCommentInfo(comment = comment, textThemeStyles = textThemeStyles)
            comment.title?.let {
                Text(
                    it, style = textThemeStyles.titleMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            comment.review?.let { Text(it, color = Color.White, overflow = TextOverflow.Ellipsis)}
        }
    }
}