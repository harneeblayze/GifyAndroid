package com.gify.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gify.data.model.GifModel
import com.gify.mobimeoappchallenge.paging.GifPagingSource
import com.gify.data.remote.service.GifRemoteService
import com.gify.data.repository.GifRepository
import com.gify.domain.model.GifItem
import com.gify.mobimeoappchallenge.BuildConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject
import javax.inject.Singleton

private const val NETWORK_PAGE_SIZE = 30

@Singleton
class GifyRepositoryImpl @Inject constructor(private val apiService: GifRemoteService)
    : GifRepository {


    override suspend fun getSearchResultStream(query: String, page:Int): Flow<GifItem> =
        apiService.searchGifs(BuildConfig.GIF_API_KEY,query, page, "en").data.map {
            GifItem(
                title = it.title,
                url = it.images.downsizedMedium.url
            )
        }.asFlow()
    /* {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GifPagingSource(apiService, query) }
        ).flow
    }*/
}