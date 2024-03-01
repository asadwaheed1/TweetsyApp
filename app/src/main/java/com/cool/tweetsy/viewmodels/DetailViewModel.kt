package com.cool.tweetsy.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cool.tweetsy.models.TweetListItem
import com.cool.tweetsy.repository.TweetsyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: TweetsyRepository,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    val tweets: StateFlow<List<TweetListItem>>
        get() = repository.tweets

    fun getTweets() {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("cat") ?: "motivation"
            repository.getTweets(category)
        }
    }

    init {
        getTweets()
    }
}