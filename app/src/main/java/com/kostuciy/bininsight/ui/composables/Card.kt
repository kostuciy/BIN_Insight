package com.kostuciy.bininsight.ui.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kostuciy.bininsight.R
import com.kostuciy.domain.model.CardInfo

@Composable
fun Card(
    cardInfo: CardInfo? = null,
    onUrlClick: (String) -> Unit,
    onPhoneClick: (String) -> Unit,
) {
    androidx.compose.material3.Card(
        modifier =
            Modifier
                .fillMaxWidth()
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
            if (cardInfo == null) {
                Title(resId = R.string.no_card_data)
                return@Card
            }
            val dataRowModifier = Modifier.fillMaxWidth().padding(start = 4.dp)
            Title(resId = R.string.card_title)
            DataRow(resId = R.string.bin, value = cardInfo.bin.toString(), modifier = dataRowModifier)
            cardInfo.coordinates?.let {
                DataRow(resId = R.string.coordinates, value = it, modifier = dataRowModifier)
            }
            cardInfo.type?.let {
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
    }
}
