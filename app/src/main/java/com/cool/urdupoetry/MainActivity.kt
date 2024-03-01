package com.cool.urdupoetry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cool.urdupoetry.API.UrduPoetryAPI
import com.cool.urdupoetry.screens.CategoryScreen
import com.cool.urdupoetry.screens.DetailScreen
import com.cool.urdupoetry.ui.theme.UrduPoetryTheme
import com.cool.urdupoetry.viewmodels.CategoryViewModel
import com.cool.urdupoetry.viewmodels.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var urduPoetryAPI: UrduPoetryAPI

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UrduPoetryTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Tweetsy")
                            }, colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Black,
                                titleContentColor = Color.White
                            )
                        )
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        App()

                    }
                }
            }
        }
    }

    @Composable
    fun App() {
        val appController = rememberNavController()
        NavHost(navController = appController, startDestination = "categoryScreen") {

            composable(
                "detailScreen/{cat}",
                arguments = listOf(navArgument("cat") { type = NavType.StringType })
            ) {
                val tweetViewModel = hiltViewModel<DetailViewModel>()
                DetailScreen(tweetViewModel)
            }
            composable("categoryScreen") {
                val categoryViewModel = hiltViewModel<CategoryViewModel>()

                CategoryScreen(categoryViewModel = categoryViewModel) { appController.navigate("detailScreen/$it") }
            }
        }
    }
}

