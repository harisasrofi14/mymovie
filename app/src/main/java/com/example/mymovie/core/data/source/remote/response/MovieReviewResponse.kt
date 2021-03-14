package com.example.mymovie.core.data.source.remote.response

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class MovieReviewResponse(

        @SerializedName("author")
        var author: String? = null,

        @SerializedName("content")
        var content: String? = null,
)