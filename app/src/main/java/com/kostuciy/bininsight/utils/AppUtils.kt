package com.kostuciy.bininsight.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object AppUtils {
    fun formatDateTime(unixTime: Long): String {
        val instant = Instant.ofEpochSecond(unixTime)
        val formatter =
            DateTimeFormatter
                .ofPattern("dd MMM yyyy, HH:mm:ss", Locale.getDefault())
                .withZone(ZoneId.systemDefault())

        return formatter.format(instant)
    }

    fun formatCoordinates(
        latitude: Float,
        longitude: Float,
    ) = "$latitude\u00B0, $longitude\u00B0"

    fun formatCardType(
        scheme: String?,
        brand: String?,
    ): String? =
        when {
            scheme == null && brand == null -> null
            scheme == null || brand == null -> brand ?: scheme?.uppercase()
            else -> "${scheme.uppercase()}, $brand"
        }
}
