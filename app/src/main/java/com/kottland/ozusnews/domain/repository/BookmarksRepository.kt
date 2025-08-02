package com.kottland.ozusnews.domain.repository

import com.kottland.ozusnews.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface BookmarksRepository {
    fun getBookmarks(): Flow<List<Article>>
    suspend fun addBookmark(article: Article)
    suspend fun removeBookmark(article: Article)
    suspend fun isBookmarked(article: Article): Boolean
}