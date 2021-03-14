package com.example.mymovie.core.data.source.local

import com.example.mymovie.core.data.source.local.entity.MovieEntity
import com.example.mymovie.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow


class LocalDataSource  private constructor(private val movieDao: MovieDao){
    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(movieDao)
            }
    }


    fun getFavoriteMovie() : Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()


    fun setFavoriteMovie(movie: MovieEntity,newState : Boolean) {
        movie.isFavorite = newState
        movieDao.insertMovie(movie)
    }

    fun getMovieState(movieId : Int) : Flow<Int>{
      return  movieDao.getMovieState(movieId)
    }

}