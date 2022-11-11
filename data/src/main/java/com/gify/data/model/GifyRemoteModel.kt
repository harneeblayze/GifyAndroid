package com.gify.data.model

import com.gify.domain.model.GifItem
import com.google.gson.annotations.SerializedName

data class GifyRemoteModel(
    val data: List<GifModel>,
    val pagination: Pagination
)

data class GifModel(
    val type: String,
    val id: String,
    val url: String,
    @SerializedName("bitly_gif_url")
    val bitlyGifUrl: String,
    val bitlyURL: String,
    val title: String,
    val images: Images
)

data class Pagination (
   val totalCount: Long,
    val count: Long,
    val offset: Long
)

data class Images(
    @SerializedName("downsized_medium")
    val downsizedMedium: DownsizedMedium
)

data class DownsizedMedium(
    var url: String
)

/*fun GifyRemoteModel.toGifItem(): GifItem {
   return data.map {
        GifItem(
            title = it.title,
            url = it.images.downsizedMedium.url
        )
    }

}*/
