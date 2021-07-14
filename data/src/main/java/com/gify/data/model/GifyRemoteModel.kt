package com.gify.data.model

data class GifyRemoteModel(
    val data: List<GifModel>,
    val pagination: Pagination
)

data class GifModel(
    val type: String,
    val id: String,
    val url: String,
    val bitly_gif_url: String,
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
    val downsized_medium: DownsizedMedium
)

data class DownsizedMedium(
    var url: String
)
