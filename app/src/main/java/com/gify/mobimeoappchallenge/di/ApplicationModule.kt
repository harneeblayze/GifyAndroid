package com.gify.mobimeoappchallenge.di


import com.gify.data.repository.GifRepository
import com.gify.repository.GifyRepositoryImpl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
 abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRepository(repository: GifyRepositoryImpl) : GifRepository
}