package com.kostuciy.bininsight.ui.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun Title(resId: Int) {
    Text(
        text = stringResource(id = resId),
        style = MaterialTheme.typography.titleSmall,
    )
}
