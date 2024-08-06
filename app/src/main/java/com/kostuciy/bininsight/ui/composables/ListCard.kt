package com.kostuciy.bininsight.ui.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kostuciy.bininsight.R
import com.kostuciy.bininsight.utils.AppUtils
import com.kostuciy.domain.model.CardInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoCard(
    cardInfo: CardInfo,
    onUrlClick: (String) -> Unit,
    onPhoneClick: (String) -> Unit,
    onCoordinatesClick: (Pair<Float, Float>) -> Unit,
    onDeleteCard: (Long) -> Unit,
    modifier: Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    val dismissState =
        rememberSwipeToDismissBoxState(
            confirmValueChange = {
                if (it == SwipeToDismissBoxValue.EndToStart) {
                    onDeleteCard(cardInfo.date)
                    return@rememberSwipeToDismissBoxState true
                }
                return@rememberSwipeToDismissBoxState false
            },
            positionalThreshold = { it * .25f },
        )
    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = { DismissBackGround(dismissState = dismissState) },
        modifier = modifier,
    ) {
        ElevatedCard(
            shape = RoundedCornerShape(16.dp),
            onClick = { expanded = !expanded },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .animateContentSize(
                        animationSpec =
                            tween(
                                durationMillis = 240,
                                easing = LinearOutSlowInEasing,
                            ),
                    ),
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                ListCardHeader(date = cardInfo.date)
                if (expanded) {
                    ListCardBody(
                        cardInfo,
                        onUrlClick,
                        onPhoneClick,
                        onCoordinatesClick,
                    )
                }
            }
        }
    }
}

@Composable
fun ListCardHeader(date: Long) {
    val formattedDate = AppUtils.formatDateTime(date)
    Text(
        text = stringResource(id = R.string.request, formattedDate),
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(vertical = 4.dp),
    )
}

@Composable
fun ListCardBody(
    cardInfo: CardInfo,
    onUrlClick: (String) -> Unit,
    onPhoneClick: (String) -> Unit,
    onCoordinatesClick: (Pair<Float, Float>) -> Unit,
) {
    val dataRowModifier = Modifier.fillMaxWidth().padding(start = 4.dp)
    Title(resId = R.string.card_title)
    DataRow(resId = R.string.bin, value = cardInfo.bin.toString(), modifier = dataRowModifier)
    cardInfo.coordinates?.let { coordinates ->
        val coordinatesString = AppUtils.formatCoordinates(coordinates.first, coordinates.second)
        DataRow(
            resId = R.string.coordinates,
            value = coordinatesString,
            modifier = dataRowModifier,
            onClick = { onCoordinatesClick(coordinates) },
        )
    }
    val type = AppUtils.formatCardType(cardInfo.scheme, cardInfo.brand)
    type?.let {
        DataRow(resId = R.string.type, value = it, modifier = dataRowModifier)
    }
    cardInfo.bankInfo?.let { bankInfo ->
        Title(resId = R.string.bank_title)
        DataRow(resId = R.string.bank_name, value = bankInfo.name, modifier = dataRowModifier)
        bankInfo.url?.let {
            DataRow(resId = R.string.bank_url, value = it, onUrlClick, modifier = dataRowModifier)
        }
        bankInfo.city?.let {
            DataRow(resId = R.string.bank_city, value = it, modifier = dataRowModifier)
        }
        bankInfo.phone?.let {
            DataRow(resId = R.string.bank_phone, value = it, onPhoneClick, modifier = dataRowModifier)
        }
    }
}
