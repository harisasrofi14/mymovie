package com.example.mymovie.core.domain.repository

import com.example.mymovie.core.data.Resource
import com.example.mymovie.core.domain.model.DetailMovie
import com.example.mymovie.core.domain.model.Movie
import com.example.mymovie.core.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getMoviesByCategory(Category: String): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun getDetailMovie(movieId: Int): Flow<Resource<DetailMovie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

    fun getMovieState(id: Int): Flow<Int>

    fun getMovieReview(id: Int): Flow<Resource<List<Review>>>

}