package com.kostuciy.data.utils

import com.kostuciy.data.db.entities.CardInfoEntity
import com.kostuciy.domain.model.CardInfo

object ModelUtils {
    fun CardInfo.toEntity() = this.let {
        CardInfoEntity(bin, country, coordinates, type, bankInfo, date)
    }
}
