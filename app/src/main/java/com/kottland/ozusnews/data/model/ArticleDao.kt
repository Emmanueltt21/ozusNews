package com.kottland.ozusnews.data.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM cached_articles ORDER BY publishedAt DESC")
    fun getAll(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<ArticleEntity>)

    @Query("DELETE FROM cached_articles")
    suspend fun clearAll()
}