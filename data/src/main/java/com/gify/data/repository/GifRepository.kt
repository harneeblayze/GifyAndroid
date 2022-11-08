package com.gify.data.repository

import androidx.paging.PagingData
import com.gify.data.model.GifModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface GifRepository {

    fun getSearchResultStream(query: String): Flow<PagingData<GifModel>>
}