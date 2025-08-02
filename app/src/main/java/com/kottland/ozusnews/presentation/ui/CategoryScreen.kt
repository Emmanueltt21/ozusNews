package com.kottland.ozusnews.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

val categories = listOf(
    "Business", "Entertainment", "General", "Health", "Science", "Sports", "Technology"
)

@Composable
fun CategoryScreen(onCategorySelected: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Categories",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(categories) { category ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable { onCategorySelected(category.lowercase()) }
                ) {
                    Text(
                        text = category,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }
}