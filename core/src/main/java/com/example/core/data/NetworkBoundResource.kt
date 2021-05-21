package com.example.core.data

import com.example.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    @ExperimentalCoroutinesApi
    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emitAll(saveCallResult(apiResponse.data).map { Resource.Success(it) })
            }
            is ApiResponse.Empty -> {
                onFetchFailed()
                emit(Resource.Error<ResultType>("Empty"))
            }
            is ApiResponse.Error -> {
                onFetchFailed()
                emit(Resource.Error<ResultType>(apiResponse.errorMessage))
            }
        }

    }

    protected open fun onFetchFailed() {

    }

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType): Flow<ResultType>

    @ExperimentalCoroutinesApi
    fun asFlow(): Flow<Resource<ResultType>> = result
}