package com.kottland.ozusnews.domain.usecase

import com.kottland.ozusnews.domain.model.Article
import com.kottland.ozusnews.domain.repository.BookmarksRepository
import kotlinx.coroutines.flow.Flow

class GetBookmarksUseCase @javax.inject.Inject constructor(private val repository: BookmarksRepository) {
    operator fun invoke() = repository.getBookmarks()
}

class AddBookmarkUseCase @javax.inject.Inject constructor(private val repository: BookmarksRepository) {
    suspend operator fun invoke(article: Article) = repository.addBookmark(article)
}

class RemoveBookmarkUseCase @javax.inject.Inject constructor(private val repository: BookmarksRepository) {
    suspend operator fun invoke(article: Article) = repository.removeBookmark(article)
}

class IsBookmarkedUseCase @javax.inject.Inject constructor(private val repository: BookmarksRepository) {
    suspend operator fun invoke(article: Article): Boolean = repository.isBookmarked(article)
}