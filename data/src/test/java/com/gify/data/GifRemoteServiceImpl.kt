package com.gify.data

import com.gify.data.factory.DataFactory
import com.gify.data.factory.GifDataFactory
import com.gify.data.model.GifyRemoteModel
import com.gify.data.remote.service.GifRemoteService

class GifRemoteServiceImpl() : GifRemoteService {

    override suspend fun searchGifs(apiKey: String, query: String, page: Int, lang: String): GifyRemoteModel {
        return GifDataFactory.makeGifyRemoteModel()
    }

}