package com.kostuciy.domain.model

data class CardInfo(
    val bin: Long,
    val country: String? = null,
    val coordinates: String? = null,
    val type: String? = null,
    val bankInfo: BankInfo? = null,
    val date: Long = System.currentTimeMillis() / 1000L,
)
