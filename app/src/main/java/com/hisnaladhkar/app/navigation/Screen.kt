package com.hisnaladhkar.app.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")

    data object Search : Screen("search")

    data object Favorites : Screen("favorites")

    data object Tasbih : Screen("tasbih")

    data object PrayerTimes : Screen("prayer_times")

    data object Settings : Screen("settings")

    data object CategoryList : Screen("category/{categoryId}") {
        fun createRoute(categoryId: Long) = "category/$categoryId"
    }

    data object DhikrDetail : Screen("dhikr/{dhikrId}") {
        fun createRoute(dhikrId: Long) = "dhikr/$dhikrId"
    }
}
