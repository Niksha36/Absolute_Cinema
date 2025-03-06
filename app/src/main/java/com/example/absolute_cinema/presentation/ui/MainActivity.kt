package com.example.absolute_cinema.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.absolute_cinema.R
import com.example.absolute_cinema.presentation.MainScreen.MainScreen
import com.example.absolute_cinema.presentation.MainScreen.MainScreenViewModel
import com.example.absolute_cinema.util.NavigationItem
import com.example.compose.Absolute_CinemaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Absolute_CinemaTheme(dynamicColor = false) {

                ConstraintLayout(
                    Modifier.fillMaxSize()
                ) {
                    Surface {
                        AppScreen()
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Absolute_CinemaTheme {
//        Greeting("Android")
//    }
//}

@Composable
fun AppScreen() {

    var selectedItem by remember{ mutableIntStateOf(0) }

    val navItems = listOf(
        NavigationItem(
            stringResource(R.string.bottomNavItemMain),
            Icons.Filled.Home
        ),
        NavigationItem(
            stringResource(R.string.bottomNavItemSearch),
            Icons.Filled.Search
        ),
        NavigationItem(
            stringResource(R.string.bottomNavItemFavorite),
            Icons.Filled.Favorite
        )
    )
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        bottomBar = {
            NavigationBar {
                navItems.forEachIndexed { index, navigationItem ->
                    NavigationBarItem(
                        label = { Text(navigationItem.label) },
                        icon = { Icon(navigationItem.icon,  contentDescription = navigationItem.label) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index}
                    )
                }
            }
        }
    ){ innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedItem) {
                0 -> {
                    val mainScreenViewModel: MainScreenViewModel = hiltViewModel()
                    val state by mainScreenViewModel.state.collectAsState()
                    MainScreen(
                        state = state,
                        onEvent = mainScreenViewModel::onEvent
                    )
                }
                1 -> {}
                2 -> {}
                // Add more cases as needed.
            }
        }
    }
}