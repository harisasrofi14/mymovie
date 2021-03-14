package com.example.mymovie.core.domain.usecase

import com.example.mymovie.core.data.Resource
import com.example.mymovie.core.domain.model.DetailMovie
import com.example.mymovie.core.domain.model.Movie
import com.example.mymovie.core.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMoviesByCategory(Category: String): Flow<Resource<List<Movie>>>
    fun getDetailMovie(movieId : Int) : Flow<Resource<DetailMovie>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovies(movie: Movie, state: Boolean)
    fun getMovieState(movieId: Int) : Flow<Int>
    fun getMovieReview(movieId: Int) : Flow<Resource<List<Review>>>
}
