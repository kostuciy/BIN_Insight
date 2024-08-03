package com.kostuciy.bininsight.list.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kostuciy.bininsight.R
import com.kostuciy.domain.model.CardInfo

@Composable
fun InfoCard(
    cardInfo: CardInfo,
    onUrlClick: () -> Unit,
    onPhoneClick: () -> Unit,
    onWebsiteClick: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    ElevatedCard(
        shape = RoundedCornerShape(16.dp),
        onClick = { expanded = !expanded },
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .animateContentSize(
                    animationSpec =
                        tween(
                            durationMillis = 240,
                            easing = LinearOutSlowInEasing,
                        ),
                ),
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            InfoCardHeader(id = cardInfo.id)
            if (expanded) {
                InfoCardBody(
                    cardInfo,
                    onUrlClick,
                    onPhoneClick,
                    onWebsiteClick,
                )
            }
        }
    }
}

@Composable
fun InfoCardHeader(id: Long) {
    Text(
        text = stringResource(id = R.string.request, id),
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(vertical = 4.dp),
    )
}

@Composable
fun Title(resId: Int) {
    Text(
        text = stringResource(id = resId),
        style = MaterialTheme.typography.titleMedium,
    )
}

@Composable
fun ColumnScope.InfoCardBody(
    cardInfo: CardInfo,
    onUrlClick: () -> Unit,
    onPhoneClick: () -> Unit,
    onWebsiteClick: () -> Unit,
) {
    Title(resId = R.string.card_title)
    DataRow(resId = R.string.bin, value = cardInfo.bin)
    DataRow(resId = R.string.coordinates, value = cardInfo.coordinates)
    DataRow(resId = R.string.type, value = cardInfo.type)
    Title(resId = R.string.bank_title)
    DataRow(resId = R.string.bank_url, value = cardInfo.bankInfo.url, onUrlClick)
    DataRow(resId = R.string.bank_city, value = cardInfo.bankInfo.city)
    DataRow(resId = R.string.bank_phone, value = cardInfo.bankInfo.phone, onPhoneClick)
    DataRow(resId = R.string.bank_website, value = cardInfo.bankInfo.website, onWebsiteClick)
}

@Composable
fun DataRow(
    resId: Int,
    value: String,
    onClick: (() -> Unit)? = null,
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
                    .clickable(onClick != null) { onClick?.invoke() }
                    .padding(start = 4.dp),
        )
    }
}

@Preview
@Composable
fun PreviewCard() {
    InfoCard(
        cardInfo = CardInfo(),
        { },
        { },
        { },
    )
}
