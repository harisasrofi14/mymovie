package com.example.mymovie.core.data.source.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @SerializedName("page")
    @Expose
    val page: Int,

    @SerializedName("total_results")
    @Expose
    val totalResults: Int,

    @SerializedName("total_pages")
    @Expose
    val totalPages: Int,

    @SerializedName("results")
    @Expose
    val results: List<MovieResponse>

)