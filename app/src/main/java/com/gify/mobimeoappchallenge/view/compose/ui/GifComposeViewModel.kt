package com.gify.mobimeoappchallenge.view.compose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.gify.data.repository.GifRepository
import com.gify.mobimeoappchallenge.paging.GifPagingSource
import com.gify.mobimeoappchallenge.paging.NETWORK_PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GifComposeViewModel@Inject constructor(
    private val repository: GifRepository
): ViewModel() {

    val composeGifs =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GifPagingSource(repository, "ha") }
        ).flow.cachedIn(viewModelScope)
}