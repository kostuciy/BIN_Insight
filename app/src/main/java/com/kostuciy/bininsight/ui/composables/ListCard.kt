package com.kostuciy.bininsight.ui.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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

@Composable
fun InfoCard(
    cardInfo: CardInfo,
    onUrlClick: (String) -> Unit,
    onPhoneClick: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

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
                )
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
fun ColumnScope.ListCardBody(
    cardInfo: CardInfo,
    onUrlClick: (String) -> Unit,
    onPhoneClick: (String) -> Unit,
) {
    Title(resId = R.string.card_title)
    DataRow(resId = R.string.bin, value = cardInfo.bin)
    DataRow(resId = R.string.coordinates, value = cardInfo.coordinates)
    DataRow(resId = R.string.type, value = cardInfo.type)
    Title(resId = R.string.bank_title)
    DataRow(resId = R.string.bank_name, value = cardInfo.bankInfo.name)
    DataRow(resId = R.string.bank_url, value = cardInfo.bankInfo.url, onUrlClick)
    DataRow(resId = R.string.bank_city, value = cardInfo.bankInfo.city)
    DataRow(resId = R.string.bank_phone, value = cardInfo.bankInfo.phone, onPhoneClick)
}
