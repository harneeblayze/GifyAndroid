package com.gify.mobimeoappchallenge.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gify.data.model.GifModel
import com.gify.data.remote.service.GifRemoteService
import com.gify.mobimeoappchallenge.BuildConfig
import retrofit2.HttpException
import java.io.IOException

private const val GIF_STARTING_PAGE_INDEX = 1
private const val NETWORK_PAGE_SIZE = 30

class GifPagingSource(
    private val service: GifRemoteService,
    private val query: String
) : PagingSource<Int, GifModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifModel> {
        val position = params.key ?: GIF_STARTING_PAGE_INDEX
        val apiQuery = query

        return try {
            val response = service.searchGifs(BuildConfig.GIF_API_KEY,apiQuery, position, "en")
            val gifs = response.data
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


    override fun getRefreshKey(state: PagingState<Int, GifModel>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}