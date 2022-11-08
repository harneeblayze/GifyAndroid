package com.gify.mobimeoappchallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.gify.data.model.GifModel
import com.gify.domain.model.GifItem
import com.gify.mobimeoappchallenge.databinding.ItemGifyBinding

class GifyPagingAdapter(private val callback:(GifItem)-> Unit): PagingDataAdapter<GifItem,
        GifyViewHolder>(GifyComparator()) {
    override fun onBindViewHolder(holder: GifyViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem!=null){
            holder.bindTo(currentItem)
        }
        //holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifyViewHolder {

        val binding = ItemGifyBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return GifyViewHolder(binding, callback, parent.context)


    }


}