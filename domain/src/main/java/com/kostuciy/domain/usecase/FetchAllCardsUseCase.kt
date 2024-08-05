package com.kostuciy.domain.usecase

import com.kostuciy.domain.model.CardInfo
import com.kostuciy.domain.model.Result
import com.kostuciy.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAllCardsUseCase @Inject constructor(
    private val repository: Repository,
) : UseCase<Unit, Flow<Result<List<CardInfo>>>>(repository) {
    override suspend fun execute(param: Unit) = repository.fetchAllFromDb()
}
