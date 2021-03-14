package com.example.mymovie.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mymovie.core.domain.model.Movie
import com.example.mymovie.core.domain.usecase.MovieUseCase
import com.example.mymovie.core.utils.DataDummy
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest {
    private lateinit var viewModel: FavoriteMovieViewModel


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieUseCase: MovieUseCase


    @Mock
    private lateinit var observer: Observer<List<Movie>>


    @Before
    fun setUp() {
        viewModel = FavoriteMovieViewModel(movieUseCase)
    }


    @Test
    fun getMovieByCategory() {
        viewModel = FavoriteMovieViewModel(movieUseCase)
        val dummyMovieFlow = flowOf(DataDummy.generateDummyMovieEntities())
        val dummyMovie = DataDummy.generateDummyMovieEntities()
        GlobalScope.launch(Dispatchers.IO) {
            whenever(movieUseCase.getFavoriteMovie()).thenReturn(dummyMovieFlow).then {
                val moviesLiveData = viewModel.getFavoriteMovie()
                assertNotNull(moviesLiveData)
                viewModel.getFavoriteMovie().observeForever(observer)
                Mockito.verify(observer)
                    .onChanged(dummyMovie)
            }
        }
    }
}