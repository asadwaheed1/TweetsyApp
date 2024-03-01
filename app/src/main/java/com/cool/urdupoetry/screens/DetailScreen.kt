package com.cool.urdupoetry.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.loader.content.Loader
import com.cool.urdupoetry.viewmodels.DetailViewModel
import kotlinx.coroutines.delay

@Composable
fun DetailScreen(tweetViewModel: DetailViewModel) {
    val tweetsList by tweetViewModel.tweets.collectAsState()
    if (tweetsList.isEmpty()) {
        Loader()
    } else {
        LazyColumn {
            items(tweetsList) {
                TweetItem(text = it.tweet)
            }
        }
    }
}

@Composable
fun TweetItem(text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(BorderStroke(1.dp, Color(0xFFCCCCCC)))
    ) {
        Text(
            text = text, Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun Loader() {
    val degree = produceState(initialValue = 0, producer = {
        while (true){
            delay(16)
            value = (value + 10) % 360
        }
    })
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                imageVector = Icons.Default.Refresh, contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .rotate(degree.value.toFloat())
            )
            Text(text = "Loading")
        }
    }
}
