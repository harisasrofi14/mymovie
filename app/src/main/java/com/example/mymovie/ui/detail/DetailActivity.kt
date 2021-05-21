package com.example.mymovie.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.core.data.Resource
import com.example.core.domain.model.DetailMovie
import com.example.core.domain.model.Movie
import com.example.core.utils.DateUtils
import com.example.mymovie.R
import com.example.mymovie.databinding.ActivityDetailBinding
import com.example.mymovie.ui.adapter.ReviewAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val movieDetailViewModel: DetailMovieViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding
    private var statusFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_DATA)

        val reviewAdapter = ReviewAdapter()


        movie?.let {
            movieDetailViewModel.getMovieState(it.id).observe(this@DetailActivity, { isFavorite ->
                if (isFavorite != null) {
                    if (isFavorite == 0) {
                        binding.btnFavorite.setImageResource(R.drawable.ic_favorite_border_red)
                        statusFavorite = false
                    } else if (isFavorite == 1) {
                        binding.btnFavorite.setImageResource(R.drawable.ic_favorite_red)
                        statusFavorite = true
                    }
                }
            })
            movieDetailViewModel.getDetailMovie(it.id).observe(this@DetailActivity, { detailMovie ->
                if (detailMovie != null) {
                    when (detailMovie) {
                        is Resource.Loading -> {
                            binding.progressBarDetail.visibility = View.VISIBLE
                            binding.cardViewDetailMovie.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            detailMovie.data?.let { it1 -> initView(it1, movie) }
                            binding.progressBarDetail.visibility = View.GONE
                            binding.cardViewDetailMovie.visibility = View.VISIBLE
                        }
                        is Resource.Error -> {
                            Toast.makeText(
                                applicationContext,
                                detailMovie.message,
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.progressBarDetail.visibility = View.GONE
                        }
                    }
                }

            })

            movieDetailViewModel.getMovieReview(it.id).observe(this, { review ->
                if (review != null) {
                    when (review) {
                        is Resource.Loading -> {
                            binding.progressBarDetail.visibility = View.VISIBLE
                            binding.rvReview.visibility = View.GONE
                            binding.tvReviewTitle.visibility = View.GONE
                        }
                        is Resource.Success -> {
                            review.data?.let { it1 -> reviewAdapter.setData(it1) }
                            binding.progressBarDetail.visibility = View.GONE
                            binding.rvReview.visibility = View.VISIBLE
                            binding.tvReviewTitle.visibility = View.VISIBLE
                        }
                        is Resource.Error -> {
                            Toast.makeText(
                                applicationContext,
                                review.message,
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.progressBarDetail.visibility = View.GONE
                            binding.rvReview.visibility = View.VISIBLE
                            binding.tvReviewTitle.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }

        with(binding.rvReview) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = reviewAdapter
        }


    }

    private fun initView(detailMovie: DetailMovie, movie: Movie) {
        binding.tvOverview.text = detailMovie.overview
        binding.tvTitle.text = detailMovie.title
        binding.tvReleaseDate.text = DateUtils.dateFormat(detailMovie.releaseDate)
        Glide.with(applicationContext)
            .load("https://image.tmdb.org/t/p/w500" + detailMovie.posterPath)
            .fitCenter()
            .into(binding.ivPoster)
        binding.btnFavorite.setOnClickListener {
            movie.isFavorite = !statusFavorite
            movieDetailViewModel.setFavorite(movie, !statusFavorite)
        }
    }

}