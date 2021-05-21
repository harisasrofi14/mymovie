package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.DetailMovie
import com.example.core.domain.model.Movie
import com.example.core.domain.model.Review
import com.example.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getMoviesByCategory(Category: String): Flow<Resource<List<Movie>>> {
        return movieRepository.getMoviesByCategory(Category)
    }

    override fun getDetailMovie(movieId: Int): Flow<Resource<DetailMovie>> {
        return movieRepository.getDetailMovie(movieId)
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return movieRepository.getFavoriteMovies()
    }

    override fun setFavoriteMovies(movie: Movie, state: Boolean) {
        return movieRepository.setFavoriteMovie(movie, state)
    }

    override fun getMovieState(movieId: Int): Flow<Int> {
        return movieRepository.getMovieState(movieId)
    }

    override fun getMovieReview(movieId: Int): Flow<Resource<List<Review>>> {
        return movieRepository.getMovieReview(movieId)
    }
}