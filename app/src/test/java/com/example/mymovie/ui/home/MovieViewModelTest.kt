package com.example.mymovie.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mymovie.core.data.Resource
import com.example.mymovie.core.domain.model.Movie
import com.example.mymovie.core.domain.usecase.MovieUseCase
import com.example.mymovie.core.utils.DataDummy
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieUseCase: MovieUseCase


    @Mock
    private lateinit var observer: Observer<Resource<List<Movie>>>


    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getMovieByCategory() {
        viewModel = MovieViewModel(movieUseCase)
        val dummyMovie = Resource.Success(DataDummy.generateDummyMovieEntities())
        val dummyMovieFlow = flowOf(Resource.Success(DataDummy.generateDummyMovieEntities()))
        val category = "popular"
        GlobalScope.launch(Dispatchers.IO) {
            whenever(movieUseCase.getMoviesByCategory(category)).thenReturn(dummyMovieFlow).then {
                val moviesLiveData = viewModel.getMoviesByCategory(category)
                assertNotNull(moviesLiveData)
                viewModel.getMoviesByCategory(category).observeForever(observer)
                Mockito.verify(observer)
                    .onChanged(dummyMovie)
            }
        }
    }


}