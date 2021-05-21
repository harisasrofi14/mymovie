package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.ListMovieResponse
import com.example.core.data.source.remote.response.ListMovieReviewResponse
import com.example.core.data.source.remote.response.MovieDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{category}")
    suspend fun getMovieByCategory(
            @Path("category") category: String,
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("page") page: Int
    ): ListMovieResponse


    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
            @Path("movie_id") movieId: Int,
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): MovieDetailResponse


    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReview(
            @Path("movie_id") movieId: Int,
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): ListMovieReviewResponse


}