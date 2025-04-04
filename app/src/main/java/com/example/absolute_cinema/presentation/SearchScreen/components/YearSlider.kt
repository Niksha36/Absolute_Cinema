package com.example.absolute_cinema.presentation.SearchScreen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.Year

@Composable
fun YearPicker(
    width: Dp,
    itemHeight: Dp,
    numberOfDisplayedItems: Int = 3,
    textStyle: TextStyle,
    textColor: Color,
    selectedTextColor: Color,
    onYearSelected: (year: String) -> Unit,
    currentlySelected: Int = Year.now().value,
) {
    val currentYear = remember { currentlySelected }
    val years = remember { (currentYear downTo currentYear - 100).map { it.toString() } }
    val initialYear = currentYear.toString()

    val itemHalfHeight = LocalDensity.current.run { itemHeight.toPx() / 2f }
    val scrollState = rememberSaveable(saver = LazyListState.Saver) {
        LazyListState(0)
    }
    var lastSelectedIndex by remember { mutableStateOf(0) }
    var itemsState by remember { mutableStateOf(years) }

    LaunchedEffect(years) {
        var targetIndex = years.indexOf(initialYear) - 1
        targetIndex += ((Int.MAX_VALUE / 2) / years.size) * years.size
        itemsState = years
        lastSelectedIndex = targetIndex
        scrollState.scrollToItem(targetIndex)
    }

    LazyColumn(
        modifier = Modifier
            .width(width)
            .height(itemHeight * numberOfDisplayedItems),
        state = scrollState,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = scrollState)
    ) {
        items(count = Int.MAX_VALUE) { i ->
            val year = itemsState[i % itemsState.size]
            Box(
                modifier = Modifier
                    .height(itemHeight)
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        val y = coordinates.positionInParent().y - itemHalfHeight
                        val parentHalfHeight = (itemHalfHeight * numberOfDisplayedItems)
                        val isSelected = (y > parentHalfHeight - itemHalfHeight && y < parentHalfHeight + itemHalfHeight)
                        val index = i - 1
                        if (isSelected && lastSelectedIndex != index) {
                            onYearSelected((year.toInt()+1).toString())
                            lastSelectedIndex = index
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = year,
                    style = textStyle,
                    color = if (lastSelectedIndex == i) selectedTextColor else textColor,
                    fontSize = if (lastSelectedIndex == i) textStyle.fontSize * 1.5f else textStyle.fontSize
                )
            }
        }
    }
}
