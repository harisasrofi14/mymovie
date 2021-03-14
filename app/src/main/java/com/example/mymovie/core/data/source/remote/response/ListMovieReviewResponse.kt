package com.example.mymovie.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieReviewResponse(
        @SerializedName("results")
        var reviews: List<MovieReviewResponse>? = null
)