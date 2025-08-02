package com.kottland.ozusnews.data.model

import com.kottland.ozusnews.domain.model.Article

fun ArticleDto.toDomain(): Article {
    return Article(
        sourceName = this.source?.name,
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content
    )
}