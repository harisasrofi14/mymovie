package com.example.core.utils

import com.example.core.data.source.local.entity.DetailMovieEntity
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.entity.ReviewEntity
import com.example.core.data.source.remote.response.MovieDetailResponse
import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.data.source.remote.response.MovieReviewResponse
import com.example.core.domain.model.DetailMovie
import com.example.core.domain.model.Movie
import com.example.core.domain.model.Review

object DataMapper {

    fun mapMovieResponsesToEntities(
            input: List<MovieResponse>
    ): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                    id = it.id,
                    posterPath = it.posterPath,
                    overview = it.overview,
                    releaseDate = it.releaseDate,
                    isFavorite = false,
                    title = it.title
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> {
        val movieList = ArrayList<Movie>()
        input.map {
            val movie = Movie(
                    id = it.id,
                    posterPath = it.posterPath,
                    overview = it.overview,
                    releaseDate = it.releaseDate,
                    title = it.title,
                    isFavorite = it.isFavorite
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapMovieDomainToEntity(input: Movie) = MovieEntity(
            id = input.id,
            posterPath = input.posterPath,
            title = input.title,
            overview = input.overview,
            releaseDate = input.releaseDate,
            isFavorite = input.isFavorite,
    )

    fun mapDetailMovieResponseToEntity(input: MovieDetailResponse): DetailMovieEntity {
        return DetailMovieEntity(
                id = input.id,
                posterPath = input.posterPath,
                title = input.title,
                overview = input.overview,
                releaseDate = input.releaseDate,
                isFavorite = false,
                category = ""
        )

    }

    fun mapDetailMovieEntityToDomain(input: DetailMovieEntity): DetailMovie {
        return DetailMovie(
                id = input.id,
                posterPath = input.posterPath,
                title = input.title,
                overview = input.overview,
                releaseDate = input.releaseDate
        )
    }

    fun mapMovieReviewToEntities(input: List<MovieReviewResponse>): List<ReviewEntity> {
        val reviewList = ArrayList<ReviewEntity>()
        input.map {
            val review = ReviewEntity(
                    author = it.author,
                    content = it.content
            )
            reviewList.add(review)

        }
        return reviewList
    }

    fun mapMovieReviewToDomain(input: List<ReviewEntity>): List<Review> {
        val reviewList = ArrayList<Review>()
        input.map {
            val review = Review(
                    author = it.author,
                    content = it.content
            )
            reviewList.add(review)
        }
        return reviewList
    }


}
