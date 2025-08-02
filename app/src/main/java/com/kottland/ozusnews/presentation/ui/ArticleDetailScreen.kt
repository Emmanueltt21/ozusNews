package com.kottland.ozusnews.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kottland.ozusnews.domain.model.Article

@Composable
fun ArticleDetailScreen(article: Article?) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = article?.title ?: "No Title",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = article?.author ?: "Unknown Author",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = article?.description ?: "No Description",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = article?.content ?: "No Content",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}