package com.kostuciy.bininsight.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.kostuciy.bininsight.ui.composables.Card
import com.kostuciy.bininsight.ui.composables.ErrorDialog
import com.kostuciy.bininsight.ui.composables.ListFab
import com.kostuciy.bininsight.ui.composables.SearchField
import com.kostuciy.bininsight.ui.navigation.Screen
import com.kostuciy.domain.model.UIState
import java.util.Locale

@Composable
fun SearchScreen(
    navController: NavHostController,
    uiState: UIState,
    onSearchClick: (Long) -> Unit,
    onDialogDismiss: () -> Unit,
) {
    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = { },
        )
    var searched by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            ListFab {
                navController.navigate(
                    Screen.LIST.name,
                ) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        if (uiState is UIState.Loading) {
            LinearProgressIndicator(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(paddingValues),
            )
        } else if (uiState is UIState.Error) {
            ErrorDialog(errorState = uiState, onDialogDismiss)
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(paddingValues = paddingValues),
        ) {
            Spacer(modifier = Modifier.weight(0.2f))
            SearchField(
                onButtonClick = { bin ->
                    searched = true
                    onSearchClick(bin)
                },
                buttonEnabled = uiState is UIState.Cards,
            )
            Card(
                cardInfo =
                    if (searched) {
                        (uiState as? UIState.Cards)?.list?.firstOrNull()
                    } else {
                        null
                    },
                onUrlClick = { url ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    launcher.launch(intent)
                },
                onPhoneClick = { phone ->
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
                    launcher.launch(intent)
                },
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
            )

            Spacer(modifier = Modifier.weight(0.8f))
        }
    }
}
