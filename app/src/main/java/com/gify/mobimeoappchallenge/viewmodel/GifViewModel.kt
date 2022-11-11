package com.gify.mobimeoappchallenge.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*

import com.gify.data.remote.networkResource.NetworkResource
import com.gify.domain.model.GifItem
import com.gify.data.repository.GifRepository
import com.gify.mobimeoappchallenge.paging.GifPagingSource
import com.gify.mobimeoappchallenge.paging.NETWORK_PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GifViewModel @Inject constructor(
    private val repository: GifRepository
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
                      val response =  Pager(
                            config = PagingConfig(
                                pageSize = NETWORK_PAGE_SIZE,
                                enablePlaceholders = false
                            ),
                            pagingSourceFactory = { GifPagingSource(repository, query) }
                        ).flow
                        //val response = repository.getSearchResultStream(query)
                        //stop progress
                        /*val res = response.map {
                                it.map {  res -> GifItem(title = res.title, url = res.images.downsizedMedium.url ) }
                        }.cachedIn(viewModelScope)*/
                        emit(NetworkResource.Success(response))
                    }catch (ex:Exception){
                        emit(NetworkResource.Error(ex.toString()))
                    }
                }
            }

        }.distinctUntilChanged().asLiveData()

    /*val composeGifs: Flow<Flow<PagingData<GifItem>>> = query
        .debounce(500L)
        .flowOn(Dispatchers.IO)
        .flatMapLatest { query ->
            flow<Flow<PagingData<GifItem>>>{
                if (query.isEmpty()){
                    //emit("")
                    //no data
                }else{
                    //emit(NetworkResource.Loading(null))
                    //loading
                    try {
                        val response =  Pager(
                            config = PagingConfig(
                                pageSize = NETWORK_PAGE_SIZE,
                                enablePlaceholders = false
                            ),
                            pagingSourceFactory = { GifPagingSource(repository, query) }
                        ).flow.cachedIn(viewModelScope)
                        //val response = repository.getSearchResultStream(query)
                        //stop progress
                        *//*val res = response.map {
                                it.map {  res -> GifItem(title = res.title, url = res.images.downsizedMedium.url ) }
                        }.cachedIn(viewModelScope)*//*
                       emit(response)
                    }catch (ex:Exception){
                        ex.toString()
                        //emit(NetworkResource.Error(ex.toString()))
                    }
                }
            }

        }.distinctUntilChanged()*/

    /*//compose impl
    val gifPager = Pager(PagingConfig(pageSize = NETWORK_PAGE_SIZE)){
val usersPager = Pager(PagingConfig(pageSize = 20)) {
        UsersDataSource(repo)
    }.flow.cachedIn(viewModelScope)
        GifPagingSource(repository,query)
    }*/

    val composeGifs =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GifPagingSource(repository, "ha") }
        ).flow.cachedIn(viewModelScope)
        /*query
            .debounce(500L)
            .flowOn(Dispatchers.IO)
            .flatMapLatest{
                query ->
                Pager(
                    config = PagingConfig(
                        pageSize = NETWORK_PAGE_SIZE,
                        enablePlaceholders = false
                    ),
                    pagingSourceFactory = { GifPagingSource(repository, query) }
                ).flow.cachedIn(viewModelScope)
            }*/


}