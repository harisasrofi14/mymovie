package com.example.mymovie.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.mymovie.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_modal.*


class MyBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var onItemClickCallback: OnItemClickCallback? = null


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_modal, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val categoryArray = resources.getStringArray(R.array.category_array)

        listViewOptions.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                categoryArray
        )
        listViewOptions.setOnItemClickListener { parent, _, position, _ ->
            val element = parent.getItemIdAtPosition(position).toInt()
            var category = ""
            when (element) {
                0 -> {
                    category = MainActivity.popular
                }
                1 -> {
                    category = MainActivity.topRated
                }
                2 -> {
                    category = MainActivity.nowPlaying
                }
            }
            category.let { onItemClickCallback?.onItemClicked(it) }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: String)
    }
}