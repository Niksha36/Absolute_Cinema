package com.example.absolute_cinema.presentation.MovieDetailScreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.absolute_cinema.domain.model.MovieComment
import com.example.absolute_cinema.domain.model.common.MovieFact
import com.example.absolute_cinema.presentation.MovieDetailScreen.components.comment.CommentCard
import com.example.absolute_cinema.presentation.MovieDetailScreen.components.FactCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandedListScreen(
    purpose: ExpandedListContentTypes,
    goBack: () -> Unit,
    listOfItems: List<Any>,
    onItemClick: (String) -> Unit = {}
) {
    BackHandler {
        goBack()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(purpose.displayNameResId)) },
                navigationIcon = {
                    IconButton(onClick = { goBack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 10.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(), verticalArrangement = spacedBy(15.dp)
            ) {
                items(listOfItems) { item ->
                    when (purpose) {
                        ExpandedListContentTypes.COMMENTS -> {
                            val comment = item as MovieComment
                            CommentCard(
                                comment = comment,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp),
                                toggleShowExpandedContent = { commentJson -> onItemClick(commentJson) }
                            )
                        }

                        ExpandedListContentTypes.Facts -> {
                            val facts = item as MovieFact
                            FactCard(fact = facts, modifier = Modifier.fillMaxWidth())
                        }
                    }
                }
            }
        }
    }
}
