package com.kottland.ozusnews.data.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkedArticleDao {
    @Query("SELECT * FROM bookmarked_articles")
    fun getAll(): Flow<List<BookmarkedArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: BookmarkedArticleEntity)

    @Delete
    suspend fun delete(article: BookmarkedArticleEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarked_articles WHERE url = :url)")
    suspend fun isBookmarked(url: String): Boolean
}