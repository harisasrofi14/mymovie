package com.example.mymovie.core.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import com.example.mymovie.core.data.source.local.LocalDataSource
import com.example.mymovie.core.data.source.local.entity.MovieEntity
import com.example.mymovie.core.data.source.remote.RemoteDataSource
import com.example.mymovie.core.data.source.remote.network.ApiResponse
import com.example.mymovie.core.utils.AppExecutors
import com.example.mymovie.core.utils.DataDummy
import com.example.mymovie.core.utils.DataMapper
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val moviesRepository = MovieRepository(remote, local, appExecutors)





    @ExperimentalCoroutinesApi
    @Test
    fun getMoviesByCategory() {
        val dummyMovies = flowOf(ApiResponse.Success(DataDummy.generateDummyMovies()))
        val category = "popular"
        GlobalScope.launch (Dispatchers.IO) {  whenever(remote.getMovieByCategory(category)).thenReturn(dummyMovies)}
        val moviesLiveData = moviesRepository.getMoviesByCategory(category).asLiveData()
        assertNotNull(moviesLiveData)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getDetailMovie(){
        val dummyMovie = flowOf(ApiResponse.Success(DataDummy.generateDetailMovieResponse()))
        GlobalScope.launch (Dispatchers.IO) {  whenever(remote.getDetailMovie(Mockito.anyInt())).thenReturn(dummyMovie)}
        val detailMovie = moviesRepository.getDetailMovie(Mockito.anyInt()).asLiveData()
        assertNotNull(detailMovie)
    }


}