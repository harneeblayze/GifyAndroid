package com.gify.data.repository

import androidx.paging.PagingData
import com.gify.data.model.GifModel
import com.gify.domain.model.GifItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface GifRepository {

    suspend fun getSearchResultStream(query: String, page:Int): Flow<GifItem>
}