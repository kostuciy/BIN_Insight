package com.kostuciy.domain.repository

import com.kostuciy.domain.model.CardInfo
import com.kostuciy.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getCardInfo(bin: Long): Flow<Result<List<CardInfo>>>

    suspend fun deleteCardInfo(date: Long): Flow<Result<List<CardInfo>>>

    suspend fun fetchAllFromDb(): Flow<Result<List<CardInfo>>>
}
