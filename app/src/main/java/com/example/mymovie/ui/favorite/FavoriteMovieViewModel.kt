package com.example.mymovie.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mymovie.core.domain.usecase.MovieUseCase

class FavoriteMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getFavoriteMovie() =
        movieUseCase.getFavoriteMovie().asLiveData()
}