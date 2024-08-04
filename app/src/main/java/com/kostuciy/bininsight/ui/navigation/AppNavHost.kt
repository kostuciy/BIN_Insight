package com.kostuciy.bininsight.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kostuciy.bininsight.MainActivity
import com.kostuciy.bininsight.ui.screens.ListScreen
import com.kostuciy.bininsight.ui.screens.SearchScreen
import com.kostuciy.bininsight.viewmodel.MainViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Search.route,
    viewModel: MainViewModel,
    activity: MainActivity, // TODO: for intents
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(NavigationItem.List.route) {
            val uiState by viewModel.state.collectAsState()
            ListScreen(
                navController,
                uiState,
                { }, // TODO: change later
                { },
//                { date -> viewModel.deleteRequest(date) }
                { viewModel.getAllRequests() },
            )
        }
        composable(NavigationItem.Search.route) {
            val uiState by viewModel.state.collectAsState()
            SearchScreen(
                navController,
                uiState,
                { }, // TODO:
                { },
                { bin -> viewModel.getRequest(bin) },
                { viewModel.getAllRequests() },
            )
        }
    }
}
