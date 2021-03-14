package com.example.mymovie.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movie")
@Parcelize
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "posterPath")
    var posterPath: String,
    @ColumnInfo(name = "overview")
    var overview: String,
    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable