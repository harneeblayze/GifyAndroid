package com.gify.mobimeoappchallenge.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gify.data.model.GifModel
import com.gify.mobimeoappchallenge.repository.GifRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class GifViewModel @Inject constructor(
    private var repository: GifRepository
): ViewModel() {

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<GifModel>>? = null

    fun searchGif(queryString: String): Flow<PagingData<GifModel>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<GifModel>> = repository.getSearchResultStream(queryString)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

}