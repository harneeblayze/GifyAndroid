package com.gify.data.factory

import com.gify.data.model.*

object GifDataFactory {

    fun makeDownsizedMedium() = DownsizedMedium(DataFactory.randomString())

    fun makeImages() = Images(makeDownsizedMedium())

    fun makePagination() = Pagination(DataFactory.randomLong(),DataFactory.randomLong(),DataFactory.randomLong())

    fun makeGifModel()= GifModel(DataFactory.randomString(),DataFactory.randomString(),DataFactory.randomString(),
        DataFactory.randomString(),DataFactory.randomString(),DataFactory.randomString(), makeImages())

    fun makeGifyRemoteModel() = GifyRemoteModel(listOf(makeGifModel(),makeGifModel()),
        makePagination())
}