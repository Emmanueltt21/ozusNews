package com.kottland.ozusnews.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_articles")
data class BookmarkedArticleEntity(
    @PrimaryKey val url: String,
    val title: String?,
    val author: String?,
    val description: String?,
    val content: String?,
    val publishedAt: String?,
    val urlToImage: String?
)