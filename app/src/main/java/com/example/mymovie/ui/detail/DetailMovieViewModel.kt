package com.example.mymovie.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.model.Movie
import com.example.core.domain.usecase.MovieUseCase


class DetailMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getDetailMovie(movieId: Int) =
            movieUseCase.getDetailMovie(movieId).asLiveData()

    fun setFavorite(movie: Movie, newState: Boolean) =
            movieUseCase.setFavoriteMovies(movie, newState)

    fun getMovieState(movieId: Int) =
            movieUseCase.getMovieState(movieId).asLiveData()

    fun getMovieReview(movieId: Int) =
            movieUseCase.getMovieReview(movieId).asLiveData()
}