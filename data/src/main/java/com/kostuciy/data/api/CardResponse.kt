package com.kostuciy.data.api

import com.kostuciy.domain.model.BankInfo
import com.kostuciy.domain.model.CardInfo

data class CardResponse(
    val brand: String? = null,
    val country: CountryResponse? = null,
    val bank: BankResponse? = null,
) {
    fun toModel(bin: Long): CardInfo {
        val coordinates =
            country?.let {
                "${it.latitude}\u00B0, ${it.longitude}\u00B0"
            }
        val bankInfo =
            bank?.let {
                BankInfo(it.name, it.phone, it.url, it.city)
            }

        return CardInfo(
            bin,
            country?.name,
            coordinates,
            brand,
            bankInfo,
        )
    }
}

data class CountryResponse(
    val name: String,
    val latitude: Int,
    val longitude: Int,
)

data class BankResponse(
    val name: String,
    val url: String? = null,
    val phone: String? = null,
    val city: String? = null,
)
