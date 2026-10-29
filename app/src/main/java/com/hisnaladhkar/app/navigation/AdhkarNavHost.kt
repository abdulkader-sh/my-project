package com.hisnaladhkar.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hisnaladhkar.app.presentation.categorylist.CategoryListScreen
import com.hisnaladhkar.app.presentation.home.HomeScreen

@Composable
fun AdhkarNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                onCategoryClick = { category ->
                    navController.navigate(Screen.CategoryList.createRoute(category.id))
                }
            )
        }
        composable(
            route = Screen.CategoryList.route,
            arguments = listOf(
                navArgument("categoryId") { type = NavType.LongType }
            )
        ) {
            CategoryListScreen()
        }
    }
}
