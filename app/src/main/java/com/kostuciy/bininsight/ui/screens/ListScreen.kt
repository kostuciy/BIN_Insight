package com.kostuciy.bininsight.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.kostuciy.bininsight.ui.composables.ErrorDialog
import com.kostuciy.bininsight.ui.composables.InfoCard
import com.kostuciy.bininsight.ui.composables.SearchFab
import com.kostuciy.bininsight.ui.navigation.Screen
import com.kostuciy.domain.model.CardInfo
import com.kostuciy.domain.model.UIState
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListScreen(
    navController: NavHostController,
    uiState: UIState,
    onDeleteCard: (Long) -> Unit,
    onDialogDismiss: () -> Unit,
) {
    var currentList by remember {
        mutableStateOf(emptyList<CardInfo>())
    }
    val listState = rememberLazyListState()
    val fabExpanded by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }
    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = { },
        )

    Scaffold(
        floatingActionButton = {
            SearchFab(
                isExpanded = fabExpanded,
                onClick = {
                    navController.navigate(
                        Screen.SEARCH.name,
                    ) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        if (uiState is UIState.Error) {
            ErrorDialog(errorState = uiState, onDialogDismiss)
        } else if (uiState is UIState.Cards) {
            currentList = uiState.list
        }

        LazyColumn(
            state = listState,
            contentPadding = paddingValues,
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(8.dp),
        ) {
            items(items = currentList, key = { it.date }) {
                InfoCard(
                    cardInfo = it,
                    onUrlClick = { url ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        launcher.launch(intent)
                    },
                    onPhoneClick = { phone ->
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
                        launcher.launch(intent)
                    },
                    onDeleteCard = onDeleteCard,
                    onCoordinatesClick = { coordinates ->
                        val uri =
                            String.format(
                                Locale.ENGLISH,
                                "geo:%f,%f",
                                coordinates.first,
                                coordinates.second,
                            )
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                        launcher.launch(intent)
                    },
                    modifier =
                        Modifier.animateItemPlacement(
                            animationSpec =
                                tween(
                                    durationMillis = 240,
                                    easing = LinearOutSlowInEasing,
                                ),
                        ),
                )
            }
        }
    }
}
