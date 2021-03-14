package com.example.mymovie.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mymovie.core.data.Resource
import com.example.mymovie.core.domain.model.DetailMovie
import com.example.mymovie.core.domain.model.Review
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
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieUseCase: MovieUseCase


    @Mock
    private lateinit var detailMovieObserver: Observer<Resource<DetailMovie>>
    private lateinit var movieStateObserver: Observer<Int>
    private lateinit var movieReviewObserver: Observer<Resource<List<Review>>>


    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieUseCase)
    }

    @Test
    fun getMovieDetail() {
        val dummyMovieFlow = flowOf(Resource.Success(DataDummy.generateDetailMovie()))
        val dummyMovie = Resource.Success(DataDummy.generateDetailMovie())
        GlobalScope.launch(Dispatchers.IO) {
            whenever(movieUseCase.getDetailMovie(Mockito.anyInt())).thenReturn(dummyMovieFlow)
                .then {
                    val moviesLiveData = viewModel.getDetailMovie(Mockito.anyInt())
                    assertNotNull(moviesLiveData)
                    viewModel.getDetailMovie(Mockito.anyInt()).observeForever(detailMovieObserver)
                    Mockito.verify(detailMovieObserver)
                        .onChanged(dummyMovie)
                }
        }
    }

    @Test
    fun getMovieState() {
        val isFavorite = 1
        val dummyMovie = flowOf(isFavorite)
        GlobalScope.launch(Dispatchers.IO) {
            whenever(movieUseCase.getMovieState(Mockito.anyInt())).thenReturn(dummyMovie).then {
                val moviesLiveData = viewModel.getMovieState(Mockito.anyInt())
                assertNotNull(moviesLiveData)
                viewModel.getMovieState(Mockito.anyInt()).observeForever(movieStateObserver)
                Mockito.verify(movieStateObserver)
                    .onChanged(isFavorite)
            }
        }
    }

    @Test
    fun getMovieReview() {
        val dummyReview = Resource.Success(DataDummy.generateDummyMoviesReview())
        val dummyReviewFlow = flowOf(Resource.Success(DataDummy.generateDummyMoviesReview()))
        GlobalScope.launch(Dispatchers.IO) {
            whenever(movieUseCase.getMovieReview(Mockito.anyInt())).thenReturn(dummyReviewFlow)
                .then {
                    val moviesLiveData = viewModel.getMovieReview(Mockito.anyInt())
                    assertNotNull(moviesLiveData)
                    viewModel.getMovieReview(Mockito.anyInt()).observeForever(movieReviewObserver)
                    Mockito.verify(movieReviewObserver)
                        .onChanged(dummyReview)
                }
        }
    }


}