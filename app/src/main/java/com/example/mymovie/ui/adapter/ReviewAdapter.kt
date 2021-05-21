package com.example.mymovie.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.Review
import com.example.mymovie.R
import com.example.mymovie.databinding.ItemReviewBinding


class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    private val mData = ArrayList<Review>()

    fun setData(items: List<Review>) {
        mData.clear()
        mData.addAll(items)
        this.notifyDataSetChanged()
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_review, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemReviewBinding.bind(itemView)
        fun bind(review: Review) {
            with(binding) {
                tvAuthor.text = review.author
                tvContent.text = review.content
            }
        }
    }
}