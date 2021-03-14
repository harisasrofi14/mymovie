package com.example.mymovie.core.data.source.local.entity

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class ReviewEntity(


        @SerializedName("author")
        var author: String? = null,

        @SerializedName("content")
        var content: String? = null,

)