package com.example.iqsectest.di.module

import android.app.Application
import com.example.iqsectest.di.manager.NavigationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideManagerView(app: Application): NavigationManager = NavigationManager(app)
}