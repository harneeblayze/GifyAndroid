package com.gify.mobimeoappchallenge.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gify.data.model.GifModel

class GifyComparator:DiffUtil.ItemCallback<GifModel>() {
    override fun areItemsTheSame(oldItem: GifModel, newItem: GifModel): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: GifModel, newItem: GifModel): Boolean =
        oldItem == newItem

}