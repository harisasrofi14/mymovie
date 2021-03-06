package com.example.mymovie.core.data

import com.example.mymovie.core.data.source.local.LocalDataSource
import com.example.mymovie.core.data.source.remote.RemoteDataSource
import com.example.mymovie.core.data.source.remote.network.ApiResponse
import com.example.mymovie.core.data.source.remote.response.MovieDetailResponse
import com.example.mymovie.core.data.source.remote.response.MovieResponse
import com.example.mymovie.core.data.source.remote.response.MovieReviewResponse
import com.example.mymovie.core.domain.model.DetailMovie
import com.example.mymovie.core.domain.model.Movie
import com.example.mymovie.core.domain.model.Review
import com.example.mymovie.core.domain.repository.IMovieRepository
import com.example.mymovie.core.utils.AppExecutors
import com.example.mymovie.core.utils.DataMapper
import com.example.mymovie.core.utils.EspressoIdlingResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class MovieRepository(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
) : IMovieRepository {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
                remoteData: RemoteDataSource,
                localData: LocalDataSource,
                appExecutors: AppExecutors
        ): MovieRepository =
                instance ?: synchronized(this) {
                    instance ?: MovieRepository(remoteData, localData, appExecutors)
                }
    }

    @ExperimentalCoroutinesApi
    override fun getMoviesByCategory(Category: String): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getMovieByCategory(Category)
            }

            override suspend fun saveCallResult(data: List<MovieResponse>): Flow<List<Movie>> {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                val newMovie = DataMapper.mapMovieEntitiesToDomain(movieList)
                return flowOf(newMovie)
            }

        }.asFlow()
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapMovieEntitiesToDomain(it)
        }
    }

    @ExperimentalCoroutinesApi
    override fun getDetailMovie(movieId: Int): Flow<Resource<DetailMovie>> =
            object : NetworkBoundResource<DetailMovie, MovieDetailResponse>() {
                override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> {
                    return remoteDataSource.getDetailMovie(movieId)
                }

                override suspend fun saveCallResult(data: MovieDetailResponse): Flow<DetailMovie> {
                    val detailMovie = DataMapper.mapDetailMovieResponseToEntity(data)
                    return flowOf(DataMapper.mapDetailMovieEntityToDomain(detailMovie))
                }

            }.asFlow()


    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

    override fun getMovieState(id: Int): Flow<Int> {
        return localDataSource.getMovieState(id)
    }

    @ExperimentalCoroutinesApi
    override fun getMovieReview(id: Int): Flow<Resource<List<Review>>> =
            object : NetworkBoundResource<List<Review>, List<MovieReviewResponse>>() {
                override suspend fun createCall(): Flow<ApiResponse<List<MovieReviewResponse>>> {
                    return remoteDataSource.getMovieReview(id)
                }

                override suspend fun saveCallResult(data: List<MovieReviewResponse>): Flow<List<Review>> {
                    val movieList = DataMapper.mapMovieReviewToEntities(data)
                    val newMovie = DataMapper.mapMovieReviewToDomain(movieList)
                    return flowOf(newMovie)
                }
            }.asFlow()


}