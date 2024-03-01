package com.cool.urdupoetry.API

import com.cool.urdupoetry.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface UrduPoetryAPI {
    @GET("/v3/b/65dec462dc74654018aae942?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String):Response<List<TweetListItem>>
    @GET("/v3/b/65dec462dc74654018aae942?meta=false")
    @Headers("X-JSON-Path:tweets..category")
    suspend fun getCategories():Response<List<String>>
}