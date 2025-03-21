package com.example.absolute_cinema.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.absolute_cinema.util.UtilFunctions.ratingColor

@Composable
fun MovieCard(
    poster: String?,
    name: String,
    rating: Double,
    onClick: () -> Unit
) {

    Column(modifier = Modifier.wrapContentSize().clickable { onClick() }) {
        Box(modifier = Modifier.wrapContentSize()) {
            AsyncImage(
                model = poster,
                contentDescription = name,
                modifier = Modifier
                    .height(240.dp)
                    .width(180.dp),
                alignment = Alignment.TopStart,
                contentScale = ContentScale.FillBounds
            )
            Box(
                modifier = Modifier.offset(x=3.dp, y=3.dp)
            ) {
                Text(
                    text = rating.toString(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                    color = Color.White,
                    modifier = Modifier
                        .background(ratingColor(rating))
                        .padding(horizontal = 5.dp, vertical = 2.dp)
                        .align(Alignment.TopStart),
                )
            }


        }

        Text(
            text = name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Left,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.width(180.dp)
        )
    }

}