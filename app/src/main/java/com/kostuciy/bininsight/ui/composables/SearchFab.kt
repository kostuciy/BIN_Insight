package com.kostuciy.bininsight.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kostuciy.bininsight.R

@Composable
fun SearchFab(
    isExpanded: Boolean,
    onClick: () -> Unit,
) {
    ExtendedFloatingActionButton(
        text = { Text(text = stringResource(id = R.string.search)) },
        icon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(id = R.string.search_description),
            )
        },
        onClick = { onClick() },
        expanded = isExpanded,
    )
}
