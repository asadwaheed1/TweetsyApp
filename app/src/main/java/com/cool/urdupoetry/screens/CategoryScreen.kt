package com.cool.urdupoetry.screens


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.cool.urdupoetry.R
import com.cool.urdupoetry.viewmodels.CategoryViewModel

@Composable
fun CategoryScreen(
    categoryViewModel: CategoryViewModel,
    onCategoryClick: (category: String) -> Unit
) {
    val categories by categoryViewModel.categories.collectAsState()
    if (categories.isEmpty()) {
        Loader()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround,

            ) {
            items(categories.distinct()) { currentCategory ->
                CategoryItem(
                    category = currentCategory,
                    onCategoryClick = onCategoryClick
                )
            }
        }
    }
}

@Composable
fun CategoryItem(category: String, onCategoryClick: (category: String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(160.dp)
            .clip(RoundedCornerShape(8.dp))
            .paint(
                painter = painterResource(id = R.drawable.bg),
                contentScale = ContentScale.Crop
            )
            .border(1.dp, color = Color(0xFFEEEEEE))
            .clickable {
                onCategoryClick(category)
            },
        contentAlignment = Alignment.BottomCenter,
    ) {
        Text(
            category,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(0.dp, 20.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

