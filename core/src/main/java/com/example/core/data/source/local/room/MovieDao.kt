package com.example.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDao {

    @Query("SELECT * FROM movie where isFavorite=1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Query("SELECT COUNT(*) FROM movie where id= :id and isFavorite= 1")
    fun getMovieState(id: Int): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieEntity)

}