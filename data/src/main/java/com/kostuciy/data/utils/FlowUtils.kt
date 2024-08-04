package com.kostuciy.data.utils

import com.kostuciy.domain.model.ErrorType
import com.kostuciy.domain.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.IOException

object FlowUtils {
    fun <T> Flow<T>.asResult(): Flow<Result<T>> =
        this
            .map<T, Result<T>> { Result.Success(it) }
            .onStart { emit(Result.Loading) }
            .catch { emit(handleError(it)) }
}

fun handleError(exception: Throwable): Result.Error {
    val type: ErrorType
    var code: Int? = null
    when (exception) {
        is HttpException -> {
            code = exception.code()
            type =
                when (exception.code()) {
                    in 400..499 -> ErrorType.CLIENT_ERROR
                    in 500..599 -> ErrorType.SERVER_ERROR
                    else -> ErrorType.HTTP_UNKNOWN_ERROR
                }
        }
        is IOException -> {
            type = ErrorType.NETWORK_ERROR
        }
        else -> {
            type = ErrorType.UNKNOWN_ERROR
        }
    }

    return Result.Error(type, code)
}
