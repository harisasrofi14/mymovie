package com.example.mymovie.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mymovie.core.data.source.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): MovieDatabase {
            if (INSTANCE == null) {
                synchronized(MovieDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MovieDatabase::class.java,
                            "movie_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE as MovieDatabase
        }
    }
}