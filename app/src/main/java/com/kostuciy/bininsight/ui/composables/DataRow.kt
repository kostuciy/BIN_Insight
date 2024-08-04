package com.kostuciy.bininsight.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun DataRow(
    resId: Int,
    value: String,
    onClick: ((String) -> Unit)? = null,
) {
    val name = stringResource(resId)

    Row(
        modifier =
            Modifier
                .fillMaxWidth(),
    ) {
        Text(
            text = "$name: ",
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            textDecoration = onClick?.let { TextDecoration.Underline } ?: TextDecoration.None,
            modifier =
                Modifier
                    .clickable(onClick != null) { onClick?.invoke(value) }
                    .padding(start = 4.dp),
        )
    }
}
