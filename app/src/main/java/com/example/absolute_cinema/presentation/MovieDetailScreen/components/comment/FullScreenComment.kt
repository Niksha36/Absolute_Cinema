package com.example.absolute_cinema.presentation.MovieDetailScreen.components.comment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.example.absolute_cinema.R
import com.example.absolute_cinema.domain.model.MovieComment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreenComment(
    comment: MovieComment,
    navBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text(stringResource(R.string.ExpandedContTypeSingleComment))  },
                navigationIcon = {
                    IconButton(onClick = { navBack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        }
    ) { paddingValues->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 10.dp)
        ) {
            Box(
                Modifier
                    .fillMaxSize()
            ) {

                Column {
                    UserCommentInfo(
                        comment = comment,
                        textThemeStyles = MaterialTheme.typography,
                        bottomPadding = 10
                    )
                    LazyColumn {
                        item {
                            comment.title?.let {
                                Text(
                                    it,
                                    style = MaterialTheme.typography.displayMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    ),
                                    modifier = Modifier.padding(bottom = 12.dp)
                                )
                            }
                            comment.review?.let {
                                Text(
                                    it,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color.White,
                                    modifier = Modifier.padding(bottom = 40.dp)
                                )
                            }
                        }
                    }

                }
            }

        }
        Box(
            Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Card() {
                Box(Modifier.padding(horizontal = 20.dp, vertical = 15.dp)) {
                    Row {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.ThumbUp,
                                contentDescription = "Лайк",
                                tint = Color.White
                            )
                            Text(comment.reviewLikes.toString(), Modifier.padding(start = 2.dp))
                        }

                        Row(
                            Modifier.padding(start = 20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.ThumbUp,
                                tint = Color.White,
                                modifier = Modifier
                                    .offset(y = 3.dp)
                                    .graphicsLayer(rotationX = 180f, rotationY = 180f),
                                contentDescription = "Дизлайк :)"
                            )
                            Text(comment.reviewDislikes.toString(), Modifier.padding(start = 2.dp))
                        }
                    }
                }

            }

        }
    }

}
