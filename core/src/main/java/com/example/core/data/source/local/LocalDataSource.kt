package com.example.core.data.source.local

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.room.MovieDao

import kotlinx.coroutines.flow.Flow


class LocalDataSource(private val movieDao: MovieDao) {

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()


    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.insertMovie(movie)
    }

    fun getMovieState(movieId: Int): Flow<Int> {
        return movieDao.getMovieState(movieId)
    }

}