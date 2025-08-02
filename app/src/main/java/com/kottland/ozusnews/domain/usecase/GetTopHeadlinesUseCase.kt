package com.kottland.ozusnews.domain.usecase

import com.kottland.ozusnews.domain.model.Article
import com.kottland.ozusnews.domain.repository.NewsRepository

class GetTopHeadlinesUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(
        country: String = "us",
        category: String? = null,
        page: Int = 1,
        pageSize: Int = 20
    ): Result<List<Article>> {
        return repository.getTopHeadlines(country, category, page, pageSize)
    }
}