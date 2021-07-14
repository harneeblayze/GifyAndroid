package com.gify.mobimeoappchallenge.repository

import androidx.paging.PagingData
import com.gify.data.model.GifModel
import com.gify.data.model.GifyRemoteModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface GifRepository {

    fun getSearchResultStream(query: String): Flow<PagingData<GifModel>>
}