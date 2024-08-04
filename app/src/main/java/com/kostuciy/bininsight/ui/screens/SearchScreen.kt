package com.kostuciy.bininsight.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kostuciy.bininsight.ui.composables.Card
import com.kostuciy.bininsight.ui.composables.ErrorDialog
import com.kostuciy.bininsight.ui.composables.ListFab
import com.kostuciy.bininsight.ui.composables.SearchField
import com.kostuciy.bininsight.ui.navigation.Screen
import com.kostuciy.domain.model.UIState

@Composable
fun SearchScreen(
    navController: NavHostController,
    uiState: UIState,
    onPhoneClick: (String) -> Unit, // TODO: change if needed
    onUrlClick: (String) -> Unit,
    onSearchClick: (String) -> Unit,
    onDialogDismiss: () -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            ListFab {
                navController.navigate(
                    Screen.LIST.name,
                ) { launchSingleTop = true }
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
            SearchField(onSearchClick, uiState is UIState.Cards)
            Card(
                cardInfo = (uiState as? UIState.Cards)?.list?.firstOrNull(), // TODO: change to last if needed
                onUrlClick = onUrlClick,
                onPhoneClick = onPhoneClick,
            )

            Spacer(modifier = Modifier.weight(0.8f))
        }
    }
}
