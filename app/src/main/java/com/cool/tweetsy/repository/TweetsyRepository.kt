package com.cool.tweetsy.repository

import com.cool.tweetsy.API.TweetsyAPI
import com.cool.tweetsy.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetsyRepository @Inject constructor(private val tweetsyAPI: TweetsyAPI) {
    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories
    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets: StateFlow<List<TweetListItem>>
        get() = _tweets

    suspend fun getCategories() {
        val response = tweetsyAPI.getCategories()
        if ((response.isSuccessful) && (!response.body().isNullOrEmpty())) {
            _categories.emit(response.body()!!)
        }
    }
    suspend fun getTweets(category: String) {
        val response = tweetsyAPI.getTweets("tweets[?(@.category==\"$category\")]")
        if ((response.isSuccessful) && (!response.body().isNullOrEmpty())) {
            _tweets.emit(response.body()!!)
        }
    }
}