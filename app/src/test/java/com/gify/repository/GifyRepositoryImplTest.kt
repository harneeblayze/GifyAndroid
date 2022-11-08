package com.gify.repository

import androidx.paging.PagingSource
import com.gify.data.model.*
import com.gify.data.remote.service.GifRemoteService
import com.gify.mobimeoappchallenge.paging.GifPagingSource
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test
import org.mockito.Mockito

class GifyRepositoryImplTest {
    val gifyService = mock<GifRemoteService>()


    @Test
    fun getSearchResultStream() {
        val query = "dogs"
        val pagingSource = GifPagingSource(gifyService,query)

    }

    @Test
    fun gifyPagingSourceLoadReturnsPagingDataWhenSuccessful(){
        val query = "dogs"
        val pagingSource = GifPagingSource(gifyService,query)
         val fakeDogs = listOf(
            GifModel("gif","3", "http://giphy.com/gifs/confused-flying-YsTs5ltWtEhnq",
            "http://gph.is/1gsWDcL", "http://giphy.com/embed/YsTs5ltWtEhnq", "Dancing dogs",
            Images(DownsizedMedium("http://giphy.com/embed/YsTs5ltWtEhnq"))
            )

        )
        val expected = PagingSource.LoadResult.Page(
            data = fakeDogs,
            prevKey = 0,
            nextKey = 1
        )

        val gifRemoteModel = GifyRemoteModel(fakeDogs, Pagination(1L,1L,2L))


        runBlocking {
            whenever(gifyService.searchGifs(Mockito.anyString(),Mockito.anyString(),Mockito.anyInt(),Mockito.anyString()
            )).thenReturn(gifRemoteModel)

            val loadResult = pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )


            assertEquals(expected, loadResult)
        }






    }
}