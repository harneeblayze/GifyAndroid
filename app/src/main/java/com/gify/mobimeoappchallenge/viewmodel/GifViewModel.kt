package com.gify.mobimeoappchallenge.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope

import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.gify.data.remote.networkResource.NetworkResource
import com.gify.domain.model.GifItem
import com.gify.data.repository.GifRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GifViewModel @Inject constructor(
    private var repository: GifRepository
): ViewModel() {

    private val query by lazy { MutableSharedFlow<String>(1) }


    fun queryChange(newQry:String){
        viewModelScope.launch {
            query.emit(newQry)
        }
    }

    fun retry(){
        queryChange(query.replayCache.first())
    }


    val gifs: LiveData<NetworkResource<Flow<PagingData<GifItem>>>> = query
        .debounce(500L)
        .flowOn(Dispatchers.IO)
        .flatMapLatest { query ->
            flow<NetworkResource<Flow<PagingData<GifItem>>>> {
                if (query.isEmpty()){
                emit(NetworkResource.Success(null))
                }else{
                    emit(NetworkResource.Loading(null))
                    try {
                        val response = repository.getSearchResultStream(query)
                        //stop progress
                        val res = response.map {
                                it.map {  res -> GifItem(title = res.title, url = res.images.downsizedMedium.url ) }
                        }.cachedIn(viewModelScope)
                        emit(NetworkResource.Success(res))
                    }catch (ex:Exception){
                        emit(NetworkResource.Error(ex.toString()))
                    }
                }
            }

        }.distinctUntilChanged().asLiveData()

}