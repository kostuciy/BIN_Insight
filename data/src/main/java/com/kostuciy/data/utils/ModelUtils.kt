package com.kostuciy.data.utils

import com.kostuciy.data.db.entities.CardInfoEntity
import com.kostuciy.domain.model.CardInfo

object ModelUtils {
    fun CardInfo.toEntity() =
        CardInfoEntity(
            bin,
            country,
            coordinates?.first,
            coordinates?.second,
            scheme,
            brand,
            bankInfo,
            date,
        )
}
