package com.gify.mobimeoappchallenge.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gify.data.model.GifModel
import com.gify.data.model.GifyRemoteModel
import com.gify.data.remote.service.GifRemoteService
import com.gify.mobimeoappchallenge.paging.GifPagingSource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

private const val NETWORK_PAGE_SIZE = 30

@Singleton
class GifyRepositoryImpl @Inject constructor(private val apiService: GifRemoteService)
    :GifRepository{


    override fun getSearchResultStream(query: String): Flow<PagingData<GifModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GifPagingSource(apiService, query) }
        ).flow
    }
}