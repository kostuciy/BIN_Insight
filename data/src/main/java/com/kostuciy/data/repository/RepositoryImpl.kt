package com.kostuciy.data.repository

import com.kostuciy.data.api.BinService
import com.kostuciy.data.db.dao.BinDao
import com.kostuciy.data.utils.FlowUtils.asResult
import com.kostuciy.data.utils.ModelUtils.toEntity
import com.kostuciy.domain.model.CardInfo
import com.kostuciy.domain.model.Result
import com.kostuciy.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val dao: BinDao,
    private val service: BinService,
) : Repository {
    override suspend fun getCardInfo(bin: Long): Flow<Result<List<CardInfo>>> =
        flow {
            val cardInfo =
                service.getInfo(bin).let { response ->
                    if (!response.isSuccessful) throw HttpException(response)
                    response.body()?.toModel(bin) ?: throw HttpException(response)
                }
            dao.saveCard(cardInfo.toEntity())
            val allCards = dao.getAllCards().map { it.toModel() }
            emit(allCards)
        }.asResult()

    override suspend fun deleteCardInfo(date: Long): Flow<Result<List<CardInfo>>> =
        flow {
            dao.deleteCard(date)
            val allCards = dao.getAllCards().map { it.toModel() }
            emit(allCards)
        }.asResult()

    override suspend fun fetchAllFromDb(): Flow<Result<List<CardInfo>>> =
        flow {
            val allCards = dao.getAllCards().map { it.toModel() }
            emit(allCards)
        }.asResult()
}
