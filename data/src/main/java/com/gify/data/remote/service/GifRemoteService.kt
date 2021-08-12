package com.gify.data.remote.service

import com.gify.data.model.GifyRemoteModel
import retrofit2.http.GET
import retrofit2.http.Query

interface GifRemoteService {

    @GET("gifs/search")
    suspend fun searchGifs(@Query("api_key") apiKey:String,
                           @Query("q") query:String,
                           @Query("offset") page:Int,
                           @Query("lang") lang:String): GifyRemoteModel
}