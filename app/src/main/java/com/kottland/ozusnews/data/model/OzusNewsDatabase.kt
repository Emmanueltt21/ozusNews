package com.kottland.ozusnews.data.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kottland.ozusnews.data.model.BookmarkedArticleEntity
import com.kottland.ozusnews.data.model.BookmarkedArticleDao
import com.kottland.ozusnews.data.model.ArticleEntity
import com.kottland.ozusnews.data.model.ArticleDao

@Database(
    entities = [BookmarkedArticleEntity::class, ArticleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class OzusNewsDatabase : RoomDatabase() {
    abstract fun bookmarkedArticleDao(): BookmarkedArticleDao
    abstract fun articleDao(): ArticleDao
}