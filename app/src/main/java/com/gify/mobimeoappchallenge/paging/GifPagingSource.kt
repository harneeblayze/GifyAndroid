package com.gify.mobimeoappchallenge.paging

import androidx.lifecycle.asLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gify.data.model.GifModel
import com.gify.data.remote.service.GifRemoteService
import com.gify.data.repository.GifRepository
import com.gify.domain.model.GifItem
import com.gify.mobimeoappchallenge.BuildConfig
import kotlinx.coroutines.flow.toList
import retrofit2.HttpException
import java.io.IOException

private const val GIF_STARTING_PAGE_INDEX = 1
 const val NETWORK_PAGE_SIZE = 30

class GifPagingSource(
    private val repository: GifRepository,
    private val query: String
) : PagingSource<Int, GifItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifItem> {
        val position = params.key ?: GIF_STARTING_PAGE_INDEX
        val apiQuery = query

        return try {
            //val response = service.searchGifs(BuildConfig.GIF_API_KEY,apiQuery, position, "en")
            val response = repository.getSearchResultStream(apiQuery,position)
            /*val gifs = response.data.map {
                gifModel ->  GifItem(title = gifModel.title, url = gifModel.images.downsizedMedium.url)
            }*/
            val  gifs = response.toList()
            val nextKey = if (gifs.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = gifs,
                prevKey = if (position == GIF_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }

    }


    override fun getRefreshKey(state: PagingState<Int, GifItem>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}