package com.example.absolute_cinema.presentation.MovieDetailScreen.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.absolute_cinema.util.UtilFunctions.extractVideoId

@Composable
fun TrailerCard(
    videoUri: String,
) {
    val videoId = extractVideoId(videoUri)
    val context = LocalContext.current
    Column(modifier = Modifier.width(150.dp).height(100.dp).clickable {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUri))
        context.startActivity(intent)
    }) {
        Box(Modifier.height(IntrinsicSize.Max), contentAlignment = Alignment.Center){
            AsyncImage(
                model = "https://img.youtube.com/vi/$videoId/0.jpg",
                contentDescription = "Video Thumbnail",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier.clip(CircleShape).background(MaterialTheme.colorScheme.surfaceBright.copy(alpha = 0.7f))) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play",
                    tint = Color.White,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }

    }
}