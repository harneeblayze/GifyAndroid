package com.gify.mobimeoappchallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.gify.mobimeoappchallenge.R
import com.gify.mobimeoappchallenge.databinding.GifLoadStateFooterViewItemBinding

class GifLoadStateViewHolder(
    private val binding: GifLoadStateFooterViewItemBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): GifLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.gif_load_state_footer_view_item, parent, false)
            val binding = GifLoadStateFooterViewItemBinding.bind(view)
            return GifLoadStateViewHolder(binding, retry)
        }
    }
}