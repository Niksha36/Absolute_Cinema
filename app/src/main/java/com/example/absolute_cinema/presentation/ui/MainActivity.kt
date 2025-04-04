package com.example.absolute_cinema.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.absolute_cinema.R
import com.example.absolute_cinema.presentation.navigation.Destination
import com.example.absolute_cinema.presentation.navigation.Navigation
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Absolute_CinemaTheme {
        AppScreen()
    }
}

@Composable
fun AppScreen() {
    val navController = rememberNavController()
    var selectedItem by remember{ mutableIntStateOf(0) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val navItems = listOf(
        NavigationItem(
            stringResource(R.string.bottomNavItemMain),
            Icons.Filled.Home,
            Destination.Home
        ),
        NavigationItem(
            stringResource(R.string.bottomNavItemSearch),
            Icons.Filled.Search,
            Destination.SearchScreens
        ),
        NavigationItem(
            stringResource(R.string.bottomNavItemFavorite),
            Icons.Filled.Favorite,
            Destination.Favorite
        )
    )
    val listOfRoutes = navItems.map{it.route::class.qualifiedName}
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        bottomBar = {

            NavigationBar {
                navItems.forEachIndexed { index, navigationItem ->
                    Log.e("NAVIGATIONN", currentRoute.toString())
                    NavigationBarItem(
                        label = { Text(navigationItem.label)},
                        icon = { Icon(navigationItem.icon,  contentDescription = navigationItem.label) },
                        //очевидно, совсем не оптимально, но я не знаю как сделать лучше
                        selected = if (currentRoute in listOfRoutes) currentRoute == navigationItem.route::class.qualifiedName else navItems[selectedItem].route==navigationItem.route,
                        onClick = {
                            selectedItem = index
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }

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
            Navigation(navController = navController)
        }
    }
}