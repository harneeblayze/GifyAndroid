package com.gify.mobimeoappchallenge.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gify.data.model.GifModel
import com.gify.domain.model.GifItem

class GifyComparator:DiffUtil.ItemCallback<GifItem>() {
    override fun areItemsTheSame(oldItem: GifItem, newItem: GifItem): Boolean =
        oldItem.url == newItem.url


    override fun areContentsTheSame(oldItem: GifItem, newItem: GifItem): Boolean =
        oldItem == newItem

}