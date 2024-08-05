package com.kostuciy.bininsight.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kostuciy.bininsight.ui.screens.ListScreen
import com.kostuciy.bininsight.ui.screens.SearchScreen
import com.kostuciy.bininsight.viewmodel.SharedViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Search.route,
    sharedViewModel: SharedViewModel,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(NavigationItem.List.route) {
            val uiState by sharedViewModel.state.collectAsState()
            ListScreen(
                navController,
                uiState,
                onDeleteCard = { date -> sharedViewModel.deleteCard(date) },
                onDialogDismiss = { sharedViewModel.fetchAllCards() },
            )
        }
        composable(NavigationItem.Search.route) {
            val uiState by sharedViewModel.state.collectAsState()
            SearchScreen(
                navController,
                uiState,
                onSearchClick = { bin -> sharedViewModel.getCard(bin) },
                onDialogDismiss = { sharedViewModel.fetchAllCards() },
            )
        }
    }
}
