package com.kostuciy.bininsight.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kostuciy.bininsight.R
import com.kostuciy.domain.model.UIState

@Composable
fun ErrorDialog(
    errorState: UIState.Error,
    onDismissRequest: () -> Unit,
) {
    val message = stringResource(id = errorState.messageRes)
    val code =
        if (errorState.code == null) {
            null
        } else {
            stringResource(id = R.string.code, errorState.code!!)
        }
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {},
        icon = {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = stringResource(id = R.string.error_title),
            )
        },
        title = {
            Text(text = stringResource(id = R.string.error_title))
        },
        text = {
            Text(text = "$message ${code ?: ""}")
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(text = stringResource(id = R.string.dismiss))
            }
        },
    )
}
