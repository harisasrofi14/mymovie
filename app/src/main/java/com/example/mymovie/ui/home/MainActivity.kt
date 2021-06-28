package com.example.mymovie.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.mymovie.R
import com.example.mymovie.databinding.ActivityMainBinding
import com.example.mymovie.ui.adapter.MovieAdapter
import com.example.mymovie.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    companion object {
        const val nowPlaying = "now_playing"
    }

    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Movies App"

        val movieAdapter = MovieAdapter()
        movieViewModel.getMoviesByCategory(nowPlaying).observe(this@MainActivity, { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> {
                        binding.progressCircular.visibility = View.VISIBLE
                        binding.rvMovie.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        movies.data?.let { movieAdapter.setData(it) }
                        binding.progressCircular.visibility = View.GONE
                        binding.rvMovie.visibility = View.VISIBLE
                    }
                    is Resource.Error -> {
                        binding.progressCircular.visibility = View.GONE
                        Toast.makeText(
                                applicationContext,
                                movies.message,
                                Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        })

        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Movie) {

                val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_DATA, data)
                }
                startActivity(intent)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.favorite_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mm_favorite -> {
                val uri = Uri.parse("mymovie://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}