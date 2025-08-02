package com.kottland.ozusnews.di

import com.kottland.ozusnews.data.api.NewsApiService
import com.kottland.ozusnews.data.repository.NewsRepositoryImpl
import com.kottland.ozusnews.domain.repository.NewsRepository
import com.kottland.ozusnews.domain.usecase.GetTopHeadlinesUseCase
import com.ozusnews.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import android.content.Context
import androidx.room.Room
import com.kottland.ozusnews.data.model.OzusNewsDatabase
import com.kottland.ozusnews.data.repository.BookmarksRepositoryImpl
import com.kottland.ozusnews.data.model.ArticleDao
import com.kottland.ozusnews.data.repository.ArticleCacheDataSource
import com.kottland.ozusnews.domain.repository.BookmarksRepository
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApiService(): NewsApiService =
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)

    @Provides
    @Singleton
    fun provideNewsRepository(
        apiService: com.kottland.ozusnews.data.api.NewsApiService,
        @ApiKey apiKey: String,
        cacheDataSource: ArticleCacheDataSource
    ): com.kottland.ozusnews.domain.repository.NewsRepository =
        NewsRepositoryImpl(apiService, apiKey, cacheDataSource)

    @Provides
    @Singleton
    fun provideGetTopHeadlinesUseCase(
        repository: NewsRepository
    ): GetTopHeadlinesUseCase = GetTopHeadlinesUseCase(repository)

    @Provides
    @Singleton
    fun provideOzusNewsDatabase(@ApplicationContext appContext: Context): OzusNewsDatabase =
        Room.databaseBuilder(appContext, OzusNewsDatabase::class.java, "ozus_news_db").build()

    @Provides
    @Singleton
    fun provideBookmarkedArticleDao(db: OzusNewsDatabase) = db.bookmarkedArticleDao()

    @Provides
    @Singleton
    fun provideBookmarksRepository(dao: com.kottland.ozusnews.data.model.BookmarkedArticleDao): BookmarksRepository =
        BookmarksRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideArticleDao(db: OzusNewsDatabase): ArticleDao = db.articleDao()

    @Provides
    @Singleton
    fun provideArticleCacheDataSource(articleDao: ArticleDao): ArticleCacheDataSource = ArticleCacheDataSource(articleDao)

    @Provides
    @ApiKey
    fun provideApiKey(): String =
        // Removed: import com.ozusnews.BuildConfig (duplicate)
        BuildConfig.NEWS_API_KEY
}