package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DetailMovie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val overview: String,
    val releaseDate: String,
) : Parcelable