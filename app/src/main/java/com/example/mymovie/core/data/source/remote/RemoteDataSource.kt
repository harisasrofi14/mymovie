package com.example.mymovie.core.data.source.remote

import android.util.Log
import com.example.mymovie.core.data.source.remote.network.ApiResponse
import com.example.mymovie.core.data.source.remote.network.ApiService
import com.example.mymovie.core.data.source.remote.response.MovieDetailResponse
import com.example.mymovie.core.data.source.remote.response.MovieResponse
import com.example.mymovie.core.data.source.remote.response.MovieReviewResponse
import com.example.mymovie.core.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource  constructor(private val apiService: ApiService) {
    companion object {
        private var key = "40ebbdf7ace0aedc176087ca768e21e2"
        private var language = "en-US"
        private var page = 1

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(service)
                }
    }

    @ExperimentalCoroutinesApi
    suspend fun getMovieByCategory(Category: String): Flow<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.getMovieByCategory(
                        Category,
                        key,
                        language,
                        page
                )
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
            EspressoIdlingResource.decrement()
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    suspend fun getDetailMovie(movieId: Int): Flow<ApiResponse<MovieDetailResponse>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.getDetailMovie(
                        movieId,
                        key,
                        language
                )
                if (null != response) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
            EspressoIdlingResource.decrement()
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieReview(movieId: Int): Flow<ApiResponse<List<MovieReviewResponse>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.getMovieReview(
                        movieId,
                        key,
                        language
                )
                val dataArray = response.reviews
                if (dataArray != null) {
                    if (dataArray.isNotEmpty()) {
                        response.reviews?.let {
                            emit(ApiResponse.Success(it))
                        }
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
            EspressoIdlingResource.decrement()
        }.flowOn(Dispatchers.IO)
    }
}
