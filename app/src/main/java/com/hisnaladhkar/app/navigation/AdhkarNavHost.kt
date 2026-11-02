package com.hisnaladhkar.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hisnaladhkar.app.presentation.categorylist.CategoryListScreen
import com.hisnaladhkar.app.presentation.dhikrdetail.DhikrDetailScreen
import com.hisnaladhkar.app.presentation.favorites.FavoritesScreen
import com.hisnaladhkar.app.presentation.home.HomeScreen
import com.hisnaladhkar.app.presentation.search.SearchScreen

private data class BottomDestination(val screen: Screen, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

private val bottomDestinations = listOf(
    BottomDestination(Screen.Home, "الرئيسية", Icons.Filled.Home),
    BottomDestination(Screen.Search, "بحث", Icons.Filled.Search),
    BottomDestination(Screen.Favorites, "المفضلة", Icons.Filled.Favorite)
)

@Composable
fun AdhkarNavHost() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination

    Scaffold(
        bottomBar = {
            val isTopLevel = bottomDestinations.any { currentRoute?.hierarchy?.any { d -> d.route == it.screen.route } == true }
            if (isTopLevel) {
                NavigationBar {
                    bottomDestinations.forEach { destination ->
                        val selected = currentRoute?.hierarchy?.any { it.route == destination.screen.route } == true
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(destination.screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(destination.icon, contentDescription = destination.label) },
                            label = { Text(destination.label) }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onCategoryClick = { category ->
                        navController.navigate(Screen.CategoryList.createRoute(category.id))
                    }
                )
            }
            composable(Screen.Search.route) {
                SearchScreen(
                    onDhikrClick = { dhikrId -> navController.navigate(Screen.DhikrDetail.createRoute(dhikrId)) }
                )
            }
            composable(Screen.Favorites.route) {
                FavoritesScreen(
                    onDhikrClick = { dhikrId -> navController.navigate(Screen.DhikrDetail.createRoute(dhikrId)) }
                )
            }
            composable(
                route = Screen.CategoryList.route,
                arguments = listOf(navArgument("categoryId") { type = NavType.LongType })
            ) {
                CategoryListScreen(
                    onDhikrClick = { dhikrId -> navController.navigate(Screen.DhikrDetail.createRoute(dhikrId)) }
                )
            }
            composable(
                route = Screen.DhikrDetail.route,
                arguments = listOf(navArgument("dhikrId") { type = NavType.LongType })
            ) {
                DhikrDetailScreen()
            }
        }
    }
}
