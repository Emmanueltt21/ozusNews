package com.kottland.ozusnews.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_articles")
data class ArticleEntity(
    @PrimaryKey val url: String,
    val sourceName: String?,
    val author: String?,
    val title: String?,
    val description: String?,
    val content: String?,
    val publishedAt: String?,
    val urlToImage: String?
)