package com.gify.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gify.data.model.GifModel
import com.gify.mobimeoappchallenge.paging.GifPagingSource
import com.gify.data.remote.service.GifRemoteService
import com.gify.data.repository.GifRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

private const val NETWORK_PAGE_SIZE = 30

@Singleton
class GifyRepositoryImpl @Inject constructor(private val apiService: GifRemoteService)
    : GifRepository {


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