package com.kottland.ozusnews.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
        categories.forEach { category ->
            Text(
                text = category,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCategorySelected(category.lowercase()) }
                    .padding(vertical = 12.dp)
            )
        }
    }
}