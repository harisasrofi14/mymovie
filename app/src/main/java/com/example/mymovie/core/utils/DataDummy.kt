package com.example.mymovie.core.utils

import com.example.mymovie.core.data.source.local.entity.MovieEntity
import com.example.mymovie.core.data.source.remote.response.MovieDetailResponse
import com.example.mymovie.core.data.source.remote.response.MovieResponse
import com.example.mymovie.core.domain.model.DetailMovie
import com.example.mymovie.core.domain.model.Movie
import com.example.mymovie.core.domain.model.Review

object DataDummy {

    fun generateDummyMovies(): List<MovieResponse> {
        val movies = ArrayList<MovieResponse>()

        movies.add(
            MovieResponse(
                100,
                "The SpongeBob Movie: Sponge on the Run",
                "/wu1uilmhM4TdluKi2ytfz8gidHf.jpg",
                "When his best friend Gary is suddenly snatched away, SpongeBob takes Patrick on a madcap mission far beyond Bikini Bottom to save their pink-shelled pal.",
                "2020-08-14"
            )
        )
        movies.add(
            MovieResponse(
                2,
                "Sponge on the Run",
                "/wu1uilmhM4TdluKi2ytfz8gidHf.jpg",
                "When his best friend Gary is suddenly snatched away, SpongeBob takes Patrick on a madcap mission far beyond Bikini Bottom to save their pink-shelled pal.",
                "2020-08-14",
            )
        )
        return movies
    }


    fun generateDummyMovieEntities(): List<Movie> {
        val movies = ArrayList<Movie>()

        movies.add(
            Movie(
                100,
                "The SpongeBob Movie: Sponge on the Run",
                "/wu1uilmhM4TdluKi2ytfz8gidHf.jpg",
                "When his best friend Gary is suddenly snatched away, SpongeBob takes Patrick on a madcap mission far beyond Bikini Bottom to save their pink-shelled pal.",
                "2020-08-14",
                false
            )
        )
        movies.add(
            Movie(
                2,
                "Sponge on the Run",
                "/wu1uilmhM4TdluKi2ytfz8gidHf.jpg",
                "When his best friend Gary is suddenly snatched away, SpongeBob takes Patrick on a madcap mission far beyond Bikini Bottom to save their pink-shelled pal.",
                "2020-08-14",
                false
            )
        )
        return movies
    }

    fun generateDetailMovieResponse() : MovieDetailResponse {
        return MovieDetailResponse(
            2,
            "Sponge on the Run",
            "/wu1uilmhM4TdluKi2ytfz8gidHf.jpg",
            "When his best friend Gary is suddenly snatched away, SpongeBob takes Patrick on a madcap mission far beyond Bikini Bottom to save their pink-shelled pal.",
            "2020-08-14"

        )
    }

    fun generateDetailMovie() : DetailMovie {
        return DetailMovie(
            2,
            "Sponge on the Run",
            "/wu1uilmhM4TdluKi2ytfz8gidHf.jpg",
            "When his best friend Gary is suddenly snatched away, SpongeBob takes Patrick on a madcap mission far beyond Bikini Bottom to save their pink-shelled pal.",
            "2020-08-14",
        )
    }

    fun generateDummyMoviesReview(): List<Review> {
        val reviews = ArrayList<Review>()

        reviews.add(
            Review(
                "The SpongeBob Movie: Sponge on the Run",
                "When his best friend Gary is suddenly snatched away, SpongeBob takes Patrick on a madcap mission far beyond Bikini Bottom to save their pink-shelled pal."
            )
        )
        reviews.add(
            Review(

                "Sponge on the Run",
                "When his best friend Gary is suddenly snatched away, SpongeBob takes Patrick on a madcap mission far beyond Bikini Bottom to save their pink-shelled pal."
            )
        )
        return reviews
    }

}