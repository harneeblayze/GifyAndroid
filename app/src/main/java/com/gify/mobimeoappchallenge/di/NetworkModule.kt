package com.gify.mobimeoappchallenge.di

import com.gify.data.remote.service.GifRemoteService
import com.gify.data.remote.service.RemoteServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideGifService() : GifRemoteService = RemoteServiceFactory.buildGifRemoteService()
}