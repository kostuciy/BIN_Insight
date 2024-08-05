package com.kostuciy.domain.usecase

import com.kostuciy.domain.repository.Repository

abstract class UseCase<T, R>(
    private val repository: Repository,
) {
    abstract suspend fun execute(param: T): R
}
