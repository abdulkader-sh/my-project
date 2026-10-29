package com.hisnaladhkar.app.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")

    data object CategoryList : Screen("category/{categoryId}") {
        fun createRoute(categoryId: Long) = "category/$categoryId"
    }

    data object DhikrDetail : Screen("dhikr/{dhikrId}") {
        fun createRoute(dhikrId: Long) = "dhikr/$dhikrId"
    }
}
