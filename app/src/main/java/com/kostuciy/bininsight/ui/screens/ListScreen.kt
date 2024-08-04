package com.kostuciy.bininsight.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.FabPosition
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.kostuciy.bininsight.ui.composables.ErrorDialog
import com.kostuciy.bininsight.ui.composables.InfoCard
import com.kostuciy.bininsight.ui.composables.SearchFab
import com.kostuciy.bininsight.ui.navigation.Screen
import com.kostuciy.domain.model.UIState

@Composable
fun ListScreen(
    navController: NavHostController,
    uiState: UIState,
    onPhoneClick: (String) -> Unit, // TODO: change if needed
    onUrlClick: (String) -> Unit,
//    onDeleteCard: (Long) -> Unit
    onDialogDismiss: () -> Unit,
) {
    val listState = rememberLazyListState()
    val fabExpanded by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }

    Scaffold(
        floatingActionButton = {
            SearchFab(
                isExpanded = fabExpanded,
                onClick = {
                    navController.navigate(
                        Screen.SEARCH.name,
                    ) { launchSingleTop = true }
                },
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        when (uiState) {
            is UIState.Error -> {
                ErrorDialog(errorState = uiState, onDialogDismiss)
            }
            is UIState.Loading -> {
                LinearProgressIndicator(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(paddingValues),
                )
            }
            is UIState.Cards -> {
                LazyColumn(
                    state = listState,
                    contentPadding = paddingValues,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(items = uiState.list, key = { it.date }) {
                        InfoCard(
                            cardInfo = it,
                            onUrlClick = onUrlClick,
                            onPhoneClick = onPhoneClick,
                        )
                    }
                }
            }
        }
    }
}
