package com.example.mymovie.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mymovie.core.domain.usecase.MovieUseCase

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getMoviesByCategory(category: String) =
        movieUseCase.getMoviesByCategory(category).asLiveData()
}