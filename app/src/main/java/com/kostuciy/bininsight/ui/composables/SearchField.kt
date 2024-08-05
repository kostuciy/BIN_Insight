package com.kostuciy.bininsight.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kostuciy.bininsight.R

@Composable
fun SearchField(
    onButtonClick: (text: Long) -> Unit,
    buttonEnabled: Boolean,
) {
    var text by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier =
            Modifier
                .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text(stringResource(id = R.string.bin)) },
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                ),
            keyboardActions =
                KeyboardActions(
                    onDone = { keyboardController?.hide() },
                ),
        )

        FilledTonalIconButton(
            onClick = {
                if (text.isNotBlank()) onButtonClick(text.toLong())
            },
            enabled = buttonEnabled,
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(id = R.string.search),
            )
        }
    }
}
