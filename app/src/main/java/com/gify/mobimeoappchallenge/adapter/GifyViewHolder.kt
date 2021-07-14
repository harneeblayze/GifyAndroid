package com.gify.mobimeoappchallenge.adapter

import androidx.recyclerview.widget.RecyclerView
import com.gify.core.utils.loadGif
import com.gify.data.model.GifModel
import com.gify.mobimeoappchallenge.databinding.ItemGifyBinding

class GifyViewHolder(
    private val binding: ItemGifyBinding,
    private val callback:(GifModel?) -> Unit

) : RecyclerView.ViewHolder(binding.root){
    fun bindTo(gifModel: GifModel?){
        binding.apply {
            sivGifImage.loadGif(gifModel?.images?.downsized_medium?.url)
            tvGifName.text = gifModel?.title

        }

        itemView.setOnClickListener {
            callback.invoke(gifModel)
        }
    }

}