package com.gify.data

import com.gify.data.model.GifModel
import com.gify.data.model.GifyRemoteModel
import com.google.gson.Gson
import com.google.gson.TypeAdapter

object GifData {
    private val gson = Gson()

    private val remoteGifAdapter: TypeAdapter<GifyRemoteModel> =
        gson.getAdapter(GifyRemoteModel::class.java)

    fun provideRemoteGifsFromAssets(): List<GifModel> {
        return remoteGifAdapter.fromJson(
            FileReader.readFileFromResources("gif_response.json")
        )?.data ?: listOf()
    }
}