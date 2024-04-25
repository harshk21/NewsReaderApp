package com.hk210.newsreaderapp.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hk210.newsreaderapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL
import java.net.URLConnection
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context.applicationContext
}
