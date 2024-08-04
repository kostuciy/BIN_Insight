package com.kostuciy.bininsight.viewmodel

import androidx.lifecycle.ViewModel
import com.kostuciy.bininsight.R
import com.kostuciy.domain.model.CardInfo
import com.kostuciy.domain.model.ErrorType
import com.kostuciy.domain.model.Result
import com.kostuciy.domain.model.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _state: MutableStateFlow<UIState> = MutableStateFlow((UIState.Loading))
    val state: StateFlow<UIState> = _state.asStateFlow()

    private suspend fun changeUIState(result: Flow<Result<List<CardInfo>>>) {
        result.collect {
            when (it) {
                is Result.Error -> getErrorState(it)
                is Result.Loading -> UIState.Loading
                is Result.Success -> UIState.Cards(it.data)
            }
        }
    }

    private fun getErrorState(result: Result.Error): UIState.Error {
        val code: Int? = result.errorCode
        val stringRes: Int =
            when (result.type) {
                ErrorType.CLIENT_ERROR -> R.string.error_client
                ErrorType.SERVER_ERROR -> R.string.error_server
                ErrorType.HTTP_UNKNOWN_ERROR -> R.string.error_unknown_http
                ErrorType.NETWORK_ERROR -> R.string.error_network
                ErrorType.UNKNOWN_ERROR -> R.string.error_unknown
            }

        return UIState.Error(stringRes, code)
    }

    fun getAllRequests(): Flow<Result<List<CardInfo>>> {
        TODO()
    }

    fun deleteRequest(date: Long): Flow<Result<List<CardInfo>>> {
        TODO()
    }

    fun getRequest(bin: String): Flow<Result<List<CardInfo>>> {
        TODO()
    }
}
