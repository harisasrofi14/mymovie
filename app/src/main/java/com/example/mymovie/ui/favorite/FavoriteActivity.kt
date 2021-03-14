package com.example.mymovie.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie.R
import com.example.mymovie.core.domain.model.Movie
import com.example.mymovie.ui.ViewModelFactory
import com.example.mymovie.ui.adapter.MovieAdapter
import com.example.mymovie.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.activity_main.*

class FavoriteActivity : AppCompatActivity() {
    private lateinit var favoriteMovieViewModel: FavoriteMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        supportActionBar?.title = "Favorite Movies"
        val factory = ViewModelFactory.getInstance(this)
        favoriteMovieViewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]

        val movieAdapter = MovieAdapter()

        favoriteMovieViewModel.getFavoriteMovie().observe(this, { favoriteMovies ->
            favoriteMovies?.let { movieAdapter.setData(it) }
        })


        with(rv_favorite_movie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }


        movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Movie) {

                val intent = Intent(this@FavoriteActivity, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_DATA, data)
                }
                startActivity(intent)
            }
        })
    }
}