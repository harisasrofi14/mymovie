package com.example.mymovie.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.domain.model.Movie
import com.example.core.utils.DateUtils
import com.example.mymovie.R
import com.example.mymovie.databinding.ItemListMovieBinding


class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private val mData = ArrayList<Movie>()
    private var onItemClickCallback: OnItemClickCallback? = null


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(items: List<Movie>) {
        mData.clear()
        mData.addAll(items)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_movie, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(movie: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + movie.posterPath)
                    .fitCenter()
                    .into(ivPoster)
                tvTitle.text = movie.title
                itemView.setOnClickListener {
                    onItemClickCallback?.onItemClicked(movie)
                }
                tvReleaseDate.text = DateUtils.dateFormat(movie.releaseDate)
                tvOverview.text = movie.overview
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Movie)
    }
}