package com.example.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.domain.model.Movie
import com.example.favorite.databinding.ActivityFavoriteBinding
import com.example.favorite.di.favoriteModule
import com.example.mymovie.ui.adapter.MovieAdapter
import com.example.mymovie.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Favorite Movies"
        loadKoinModules(favoriteModule)
        val movieAdapter = MovieAdapter()

        favoriteMovieViewModel.getFavoriteMovie().observe(this) { favoriteMovies ->
            favoriteMovies.let { movieAdapter.setData(it) }
            if (favoriteMovies.isEmpty()) {
                binding.animation.visibility = View.VISIBLE
            }
        }


        with(binding.rvFavorite) {
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