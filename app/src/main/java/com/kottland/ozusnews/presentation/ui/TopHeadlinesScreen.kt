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
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun TopHeadlinesScreen(
    viewModel: NewsViewModel,
    onArticleClick: (com.kottland.ozusnews.domain.model.Article) -> Unit = {},
    onCategoryClick: () -> Unit = {},
    onBookmark: (com.kottland.ozusnews.domain.model.Article) -> Unit = {}
) {
    val articles = viewModel.articles.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val error = viewModel.error.collectAsState().value
    val categories = listOf("All", "Business", "Entertainment", "General", "Health", "Science", "Sports", "Technology")
    var selectedCategory by remember { mutableStateOf("All") }
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Ozus News",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 24.dp)
        )
        androidx.compose.foundation.lazy.LazyRow(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(categories) { category ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable {
                            selectedCategory = category
                            viewModel.fetchTopHeadlines(if (category == "All") "" else category.lowercase())
                        }
                ) {
                    Text(
                        text = category,
                        style = if (category == selectedCategory) MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold) else MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
       /* Text(
            text = "Browse by Category",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp)
                .clickable { onCategoryClick() }
        )*/
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
                                Row(
                                    modifier = Modifier
                                        .padding(12.dp)
                                        .fillMaxWidth()
                                        .clickable { onArticleClick(article) }
                                ) {
                                    Image(
                                        painter = rememberAsyncImagePainter(article.urlToImage),
                                        contentDescription = article.title,
                                        modifier = Modifier
                                            .size(100.dp)
                                            .aspectRatio(1.6f)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = article.title ?: "No Title",
                                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = article.description ?: "",
                                            style = MaterialTheme.typography.bodyMedium,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = article.publishedAt ?: "",
                                            style = MaterialTheme.typography.bodySmall,
                                            textAlign = TextAlign.End
                                        )
                                    }
                                    IconButton(onClick = { onBookmark(article) }) {
                                        androidx.compose.material3.Icon(Icons.Default.Favorite, contentDescription = "Bookmark")
                                    }
                                }
                                Divider()
                            }
                        }
                    }
                }
            }
        }
    }
}