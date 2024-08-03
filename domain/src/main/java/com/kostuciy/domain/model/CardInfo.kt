package com.kostuciy.domain.model

data class CardInfo(
    val id: Long = 1L,
    val bin: String = "35643r2342343423413",
    val country: String = "Russia",
    val coordinates: String = "56.23245245 12.31153513",
    val type: String = "VISA",
    val bankInfo: BankInfo = BankInfo(),
)
