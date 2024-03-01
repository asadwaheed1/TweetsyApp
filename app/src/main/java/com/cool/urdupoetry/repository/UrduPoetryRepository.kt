package com.cool.urdupoetry.repository

import com.cool.urdupoetry.API.UrduPoetryAPI
import com.cool.urdupoetry.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class UrduPoetryRepository @Inject constructor(private val urduPoetryAPI: UrduPoetryAPI) {
    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories
    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets: StateFlow<List<TweetListItem>>
        get() = _tweets

    suspend fun getCategories() {
        val response = urduPoetryAPI.getCategories()
        if ((response.isSuccessful) && (!response.body().isNullOrEmpty())) {
            _categories.emit(response.body()!!)
        }
    }
    suspend fun getTweets(category: String) {
        val response = urduPoetryAPI.getTweets("tweets[?(@.category==\"$category\")]")
        if ((response.isSuccessful) && (!response.body().isNullOrEmpty())) {
            _tweets.emit(response.body()!!)
        }
    }
}