package com.kostuciy.domain.model

sealed class Result<out T> {
    data class Success<out T>(
        val data: T,
    ) : Result<T>()

    data class Error(
        val type: ErrorType,
        val errorCode: Int?,
    ) : Result<Nothing>()

    data object Loading : Result<Nothing>()
}

enum class ErrorType {
    CLIENT_ERROR,
    SERVER_ERROR,
    HTTP_UNKNOWN_ERROR,
    NETWORK_ERROR,
    UNKNOWN_ERROR,
}
