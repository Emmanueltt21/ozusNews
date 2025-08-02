package com.kottland.ozusnews.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kottland.ozusnews.presentation.viewmodel.NewsViewModel

@Composable
fun TopHeadlinesScreen(
    viewModel: NewsViewModel,
    onArticleClick: (com.kottland.ozusnews.domain.model.Article) -> Unit = {},
    onCategoryClick: () -> Unit = {}
) {
    val articles = viewModel.articles.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val error = viewModel.error.collectAsState().value

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Ozus News",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 24.dp)
        )
        Text(
            text = "Browse by Category",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp)
                .clickable { onCategoryClick() }
        )
        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                error != null -> {
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    if (articles.isEmpty()) {
                        Text(
                            text = "No news articles available.",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    } else {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(articles) { article ->
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .clickable { onArticleClick(article) }
                                ) {
                                    Text(text = article.title ?: "No Title", style = MaterialTheme.typography.titleMedium)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = article.description ?: "", style = MaterialTheme.typography.bodyMedium)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}