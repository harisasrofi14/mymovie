package com.example.core.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DetailMovieEntity(
    val id: Int,
    val title: String,
    val posterPath: String,
    val overview: String,
    val releaseDate: String,
    val category : String,
    val isFavorite : Boolean
) : Parcelable