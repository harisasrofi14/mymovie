package com.example.mymovie.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(


    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("poster_path")
    val posterPath: String,
    @field:SerializedName("overview")
    val overview: String,
    @field:SerializedName("release_date")
    val releaseDate: String,

    )