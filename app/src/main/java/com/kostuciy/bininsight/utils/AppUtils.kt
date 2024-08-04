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
}
