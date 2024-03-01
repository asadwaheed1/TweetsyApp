package com.cool.urdupoetry.di

import com.cool.urdupoetry.API.UrduPoetryAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun providesRetrofit():Retrofit{
        return Retrofit.Builder().baseUrl("https://api.jsonbin.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun providesUrduPoetryAPI(retrofit: Retrofit): UrduPoetryAPI{
        return retrofit.create(UrduPoetryAPI::class.java)
    }
}