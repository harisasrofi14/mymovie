package com.example.mymovie.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mymovie.R
import com.example.mymovie.core.data.Resource
import com.example.mymovie.core.domain.model.DetailMovie
import com.example.mymovie.core.domain.model.Movie
import com.example.mymovie.core.utils.DateUtils
import com.example.mymovie.ui.ViewModelFactory
import com.example.mymovie.ui.adapter.ReviewAdapter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var movieDetailViewModel: DetailMovieViewModel
    var statusFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val movie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        val factory = ViewModelFactory.getInstance(this)
        val reviewAdapter = ReviewAdapter()

        movieDetailViewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        movie?.let {
            movieDetailViewModel.getMovieState(it.id).observe(this@DetailActivity, { isFavorite ->
                if (isFavorite != null) {
                    if (isFavorite == 0) {
                        btn_favorite.setImageResource(R.drawable.ic_favorite_border_red)
                        statusFavorite = false
                    } else if (isFavorite == 1) {
                        btn_favorite.setImageResource(R.drawable.ic_favorite_red)
                        statusFavorite = true
                    }
                }
            })
            movieDetailViewModel.getDetailMovie(it.id).observe(this@DetailActivity, { detailMovie ->
                if (detailMovie != null) {
                    when (detailMovie) {
                        is Resource.Loading -> {
                            progress_bar_detail.visibility = View.VISIBLE
                            card_view_detail_movie.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            detailMovie.data?.let { it1 -> initView(it1, movie) }
                            progress_bar_detail.visibility = View.GONE
                            card_view_detail_movie.visibility = View.VISIBLE
                        }
                        is Resource.Error -> {
                            Toast.makeText(
                                    applicationContext,
                                    detailMovie.message,
                                    Toast.LENGTH_SHORT
                            ).show()
                            progress_bar_detail.visibility = View.GONE
                        }
                    }
                }

            })

            movieDetailViewModel.getMovieReview(it.id).observe(this, { review ->
                if (review != null) {
                    when (review) {
                        is Resource.Loading -> {
                            progress_bar_detail.visibility = View.VISIBLE
                            rv_review.visibility = View.GONE
                            tv_review_title.visibility = View.GONE
                        }
                        is Resource.Success -> {
                            review.data?.let { it1 -> reviewAdapter.setData(it1) }
                            progress_bar_detail.visibility = View.GONE
                            rv_review.visibility = View.VISIBLE
                            tv_review_title.visibility = View.VISIBLE
                        }
                        is Resource.Error -> {
                            Toast.makeText(
                                    applicationContext,
                                    review.message,
                                    Toast.LENGTH_SHORT
                            ).show()
                            progress_bar_detail.visibility = View.GONE
                            rv_review.visibility = View.VISIBLE
                            tv_review_title.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }

        with(rv_review) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = reviewAdapter
        }


    }

    private fun initView(detailMovie: DetailMovie, movie: Movie) {
        tv_overview.text = detailMovie.overview
        tv_title.text = detailMovie.title
        tv_release_date.text = DateUtils.dateFormat(detailMovie.releaseDate)
        Glide.with(applicationContext)
                .load("https://image.tmdb.org/t/p/w500" + detailMovie.posterPath)
                .fitCenter()
                .into(iv_poster)
        btn_favorite.setOnClickListener {
            movie.isFavorite = !statusFavorite
            movieDetailViewModel.setFavorite(movie, !statusFavorite)
        }
    }

}