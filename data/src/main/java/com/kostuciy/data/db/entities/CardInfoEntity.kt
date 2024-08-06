package com.kostuciy.data.db.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kostuciy.domain.model.BankInfo
import com.kostuciy.domain.model.CardInfo

@Entity
class CardInfoEntity(
    val bin: Long,
    val country: String? = null,
    val latitude: Float? = null,
    val longitude: Float? = null,
    val scheme: String? = null,
    val brand: String? = null,
    @Embedded val bankInfo: BankInfo? = null,
    @PrimaryKey val date: Long,
) {
    fun toModel(): CardInfo {
        val coordinates =
            if (latitude == null || longitude == null) {
                null
            } else {
                Pair(latitude, longitude)
            }

        return CardInfo(bin, country, coordinates, scheme, brand, bankInfo, date)
    }
}
