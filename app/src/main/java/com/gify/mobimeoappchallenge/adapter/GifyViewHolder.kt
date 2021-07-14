package com.gify.mobimeoappchallenge.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.gify.core.utils.getProgressDrawable
import com.gify.core.utils.loadGif
import com.gify.data.model.GifModel
import com.gify.mobimeoappchallenge.databinding.ItemGifyBinding

class GifyViewHolder(
    private val binding: ItemGifyBinding,
    private val callback:(GifModel?) -> Unit, val context: Context

) : RecyclerView.ViewHolder(binding.root){
    fun bindTo(gifModel: GifModel?){
        binding.apply {
            sivGifImage.loadGif(gifModel?.images?.downsizedMedium?.url, getProgressDrawable(context))
            tvGifName.text = gifModel?.title

        }

        itemView.setOnClickListener {
            callback.invoke(gifModel)
        }
    }

}