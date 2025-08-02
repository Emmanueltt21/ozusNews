package com.kottland.ozusnews.data.repository

import com.kottland.ozusnews.data.api.NewsApiService
import com.kottland.ozusnews.data.model.toDomain
import com.kottland.ozusnews.domain.model.Article
import com.kottland.ozusnews.domain.repository.NewsRepository
import com.kottland.ozusnews.data.repository.ArticleCacheDataSource
import com.kottland.ozusnews.data.model.ArticleEntity
import kotlinx.coroutines.flow.firstOrNull

class NewsRepositoryImpl(
    private val apiService: NewsApiService,
    private val apiKey: String,
    private val cacheDataSource: ArticleCacheDataSource
) : NewsRepository {
    override suspend fun getTopHeadlines(
        country: String,
        category: String?,
        page: Int,
        pageSize: Int
    ): Result<List<Article>> {
        return try {
            val response = apiService.getTopHeadlines(
                country = country,
                category = category,
                page = page,
                pageSize = pageSize,
                apiKey = apiKey
            )
            if (response.isSuccessful) {
                val articles = response.body()?.articles?.map { it.toDomain() } ?: emptyList()
                // Cache articles
                cacheDataSource.cacheArticles(articles.map { article ->
                    ArticleEntity(
                        url = article.url ?: "",
                        sourceName = article.sourceName ?: "",
                        author = article.author,
                        title = article.title ?: "",
                        description = article.description,
                        content = article.content,
                        publishedAt = article.publishedAt,
                        urlToImage = article.urlToImage
                    )
                })
                Result.success(articles)
            } else {
                // On API error, return cached articles
                val cachedList = cacheDataSource.getCachedArticles().firstOrNull() ?: emptyList()
                Result.success(cachedList.map { cachedArticle ->
                    Article(
                        sourceName = cachedArticle.sourceName,
                        author = cachedArticle.author,
                        title = cachedArticle.title,
                        description = cachedArticle.description,
                        url = cachedArticle.url,
                        urlToImage = cachedArticle.urlToImage,
                        publishedAt = cachedArticle.publishedAt,
                        content = cachedArticle.content
                    )
                })
            }
        } catch (e: Exception) {
            val cachedList = cacheDataSource.getCachedArticles().firstOrNull() ?: emptyList()
            Result.success(cachedList.map { cachedArticle ->
                Article(
                    sourceName = cachedArticle.sourceName,
                    author = cachedArticle.author,
                    title = cachedArticle.title,
                    description = cachedArticle.description,
                    url = cachedArticle.url,
                    urlToImage = cachedArticle.urlToImage,
                    publishedAt = cachedArticle.publishedAt,
                    content = cachedArticle.content
                )
            })
        }
    }
}