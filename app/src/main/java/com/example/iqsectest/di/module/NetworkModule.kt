package com.example.iqsectest.di.module

import com.example.iqsectest.di.manager.RetrofitManager
import com.example.iqsectest.network.ApiService
import com.example.iqsectest.network.ClientRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val clientRetrofit: ClientRetrofit = ClientRetrofit()

    @Singleton
    @Provides
    fun provideRetrofit(): RetrofitManager {
        return RetrofitManager(clientRetrofit.getClient().create(ApiService::class.java))
    }
}