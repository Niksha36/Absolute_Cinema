package com.example.absolute_cinema.presentation.SearchScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.Absolute_CinemaTheme

@Composable
fun SearchOptionsPanel(
    navigateToFilterScreen: () -> Unit,
    navigateToSearchingScreen: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.outlineVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    navigateToSearchingScreen()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    contentDescription = "search_icon",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    "Фильмы, сериалы, мультфильмы",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            VerticalDivider(color = MaterialTheme.colorScheme.onSurfaceVariant, thickness = 2.dp)
            Box(Modifier.fillMaxHeight()) {

                Icon(
                    imageVector = Icons.Default.Menu,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    contentDescription = "search_icon",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            navigateToFilterScreen()
                        }
                )
            }

        }
    }
}

@Preview(device = "spec:width=360dp,height=640dp,orientation=portrait", showSystemUi = true)
@Composable
private fun Search() {
    Box(Modifier.padding(top = 50.dp)) {
        Absolute_CinemaTheme(darkTheme = true) {
            SearchOptionsPanel({},{})
        }
    }

}