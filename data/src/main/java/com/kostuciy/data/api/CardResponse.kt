package com.kostuciy.data.api

import com.kostuciy.domain.model.BankInfo
import com.kostuciy.domain.model.CardInfo

data class CardResponse(
    val brand: String? = null,
    val scheme: String? = null,
    val country: CountryResponse? = null,
    val bank: BankResponse? = null,
) {
    fun toModel(bin: Long): CardInfo {
        val coordinates =
            if (country?.latitude == null || country.longitude == null) {
                null
            } else {
                Pair(country.latitude, country.longitude)
            }
        val bankInfo =
            bank?.let {
                BankInfo(it.name, it.phone, it.url, it.city)
            }

        return CardInfo(bin, country?.name, coordinates, scheme, brand, bankInfo)
    }
}

data class CountryResponse(
    val name: String,
    val latitude: Float? = null,
    val longitude: Float? = null,
)

data class BankResponse(
    val name: String,
    val url: String? = null,
    val phone: String? = null,
    val city: String? = null,
)
