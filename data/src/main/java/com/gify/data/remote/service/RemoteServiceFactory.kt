package com.gify.data.remote.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RemoteServiceFactory {
    fun buildOkhttpLogger(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return loggingInterceptor
    }

    fun buildOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    fun buildGifyService(okHttpClient: OkHttpClient): GifRemoteService{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.giphy.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(GifRemoteService::class.java)
    }

    fun buildGifRemoteService() : GifRemoteService{
        return buildGifyService(buildOkhttpClient(buildOkhttpLogger()))
    }
}