package com.gify.mobimeoappchallenge.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.gify.mobimeoappchallenge.utils.getProgressDrawable
import com.gify.mobimeoappchallenge.utils.loadGif
import com.gify.data.model.GifModel
import com.gify.domain.model.GifItem
import com.gify.mobimeoappchallenge.R
import com.gify.mobimeoappchallenge.databinding.ItemGifyBinding

class GifyViewHolder(
    private val binding: ItemGifyBinding,
    private val callback:(GifItem) -> Unit, val context: Context

) : RecyclerView.ViewHolder(binding.root){
    fun bindTo(gifItem: GifItem){
        binding.apply {
            gifComponent.gifTitle = gifItem.title
            /*val size =  context.resources.
            getDimension(R.dimen.dimen_12sp)
            gifComponent.gifTitleTextSize = size*/
            gifComponent.setGifUrl(gifItem.url)
            //sivGifImage.loadGif(gifItem.url/*gifModel?.images?.downsizedMedium?.url*/, getProgressDrawable(context))
            //tvGifName.text = gifItem.title

        }

        itemView.setOnClickListener {
            callback.invoke(gifItem)
        }
    }

}