package com.kottland.ozusnews.domain.repository

import com.kottland.ozusnews.domain.model.Article

interface NewsRepository {
    suspend fun getTopHeadlines(
        country: String = "us",
        category: String? = null,
        page: Int = 1,
        pageSize: Int = 20
    ): Result<List<Article>>
}