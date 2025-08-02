package com.kottland.ozusnews.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kottland.ozusnews.domain.model.Article
import com.kottland.ozusnews.presentation.viewmodel.BookmarksViewModel

@Composable
fun BookmarksScreen(
    viewModel: BookmarksViewModel,
    onArticleClick: (Article) -> Unit = {}
) {
    val bookmarks = viewModel.bookmarks.collectAsState().value
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Bookmarks",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 24.dp)
        )
        if (bookmarks.isEmpty()) {
            Text(
                text = "No bookmarks yet.",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(bookmarks) { article ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onArticleClick(article) }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = article.title ?: "No Title", style = MaterialTheme.typography.titleMedium)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = article.description ?: "", style = MaterialTheme.typography.bodyMedium)
                        }
                        IconButton(onClick = { viewModel.removeBookmark(article) }) {
                            androidx.compose.material3.Icon(Icons.Default.Delete, contentDescription = "Remove")
                        }
                    }
                }
            }
        }
    }
}