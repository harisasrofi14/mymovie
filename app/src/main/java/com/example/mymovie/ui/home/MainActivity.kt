package com.example.mymovie.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie.R
import com.example.mymovie.core.data.Resource
import com.example.mymovie.core.domain.model.Movie
import com.example.mymovie.ui.ViewModelFactory
import com.example.mymovie.ui.adapter.MovieAdapter
import com.example.mymovie.ui.detail.DetailActivity
import com.example.mymovie.ui.favorite.FavoriteActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        val popular = "popular"
        val topRated = "top_rated"
        val nowPlaying = "now_playing"
    }

    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Movies App"
        val factory = ViewModelFactory.getInstance(this)
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        val movieAdapter = MovieAdapter()

        val myBottomSheetDialogFragment = MyBottomSheetDialogFragment()
        buttonBottomSheetPersistent.setOnClickListener {
            myBottomSheetDialogFragment.apply {
                show(supportFragmentManager, tag)
            }

        }
        myBottomSheetDialogFragment.setOnItemClickCallback(object : MyBottomSheetDialogFragment.OnItemClickCallback {
            override fun onItemClicked(data: String) {
                myBottomSheetDialogFragment.dismiss()

                movieViewModel.getMoviesByCategory(data).observe(this@MainActivity, { movies ->
                    if (movies != null) {
                        when (movies) {
                            is Resource.Loading -> {
                                progress_circular.visibility = View.VISIBLE
                                rv_movie.visibility = View.GONE
                            }
                            is Resource.Success -> {
                                movies.data?.let { movieAdapter.setData(it) }
                                progress_circular.visibility = View.GONE
                                rv_movie.visibility = View.VISIBLE
                            }
                            is Resource.Error -> {
                                progress_circular.visibility = View.GONE
                                Toast.makeText(
                                        applicationContext,
                                        movies.message,
                                        Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                })

            }
        })

        with(rv_movie) {
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
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}