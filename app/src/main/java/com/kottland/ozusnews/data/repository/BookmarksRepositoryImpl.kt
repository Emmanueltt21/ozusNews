package com.kottland.ozusnews.data.repository

import com.kottland.ozusnews.data.model.BookmarkedArticleDao
import com.kottland.ozusnews.data.model.BookmarkedArticleEntity
import com.kottland.ozusnews.domain.model.Article
import com.kottland.ozusnews.domain.repository.BookmarksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookmarksRepositoryImpl(private val dao: BookmarkedArticleDao) : BookmarksRepository {
    override fun getBookmarks(): Flow<List<Article>> =
        dao.getAll().map { list -> list.map { it.toDomain() } }

    override suspend fun addBookmark(article: Article) {
        dao.insert(article.toEntity())
    }

    override suspend fun removeBookmark(article: Article) {
        dao.delete(article.toEntity())
    }

    override suspend fun isBookmarked(article: Article): Boolean =
        dao.isBookmarked(article.url ?: "")
}

private fun BookmarkedArticleEntity.toDomain() = Article(
    url = url,
    title = title ?: "",
    author = author,
    description = description,
    content = content,
    publishedAt = publishedAt,
    urlToImage = urlToImage,
    sourceName = ""
)

private fun Article.toEntity() = BookmarkedArticleEntity(
    url = url ?: "",
    title = title ?: "",
    author = author,
    description = description,
    content = content,
    publishedAt = publishedAt,
    urlToImage = urlToImage
)