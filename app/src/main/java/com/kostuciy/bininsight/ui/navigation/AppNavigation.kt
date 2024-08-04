package com.kostuciy.bininsight.ui.navigation

enum class Screen {
    SEARCH,
    LIST,
}

sealed class NavigationItem(
    val route: String,
) {
    data object Search : NavigationItem(Screen.SEARCH.name)

    data object List : NavigationItem(Screen.LIST.name)
}
