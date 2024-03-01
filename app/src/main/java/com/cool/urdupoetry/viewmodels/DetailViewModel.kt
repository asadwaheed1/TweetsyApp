package com.cool.urdupoetry.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cool.urdupoetry.models.TweetListItem
import com.cool.urdupoetry.repository.UrduPoetryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: UrduPoetryRepository,
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