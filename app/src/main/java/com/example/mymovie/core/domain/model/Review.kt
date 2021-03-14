package com.example.mymovie.core.domain.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Review(
        @SerializedName("author")
        var author: String? = null,

        @SerializedName("content")
        var content: String? = null,

)