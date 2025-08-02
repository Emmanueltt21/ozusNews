package com.kottland.ozusnews.data.model

data class NewsApiResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
)