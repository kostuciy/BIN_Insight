package com.kostuciy.domain.model

data class BankInfo(
    val name: String,
    val phone: String? = null,
    val url: String? = null,
    val city: String? = null,
)
