package com.kottland.ozusnews.data.repository

import com.kottland.ozusnews.data.model.ArticleDao
import com.kottland.ozusnews.data.model.ArticleEntity
import kotlinx.coroutines.flow.Flow

class ArticleCacheDataSource(private val dao: ArticleDao) {
    fun getCachedArticles(): Flow<List<ArticleEntity>> = dao.getAll()
    suspend fun cacheArticles(articles: List<ArticleEntity>) = dao.insertAll(articles)
    suspend fun clearCache() = dao.clearAll()
}