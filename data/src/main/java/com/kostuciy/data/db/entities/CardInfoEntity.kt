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
    val coordinates: String? = null,
    val type: String? = null,
    @Embedded val bankInfo: BankInfo? = null,
    @PrimaryKey val date: Long,
) {
    fun toModel() = CardInfo(bin, country, coordinates, type, bankInfo, date)
}
