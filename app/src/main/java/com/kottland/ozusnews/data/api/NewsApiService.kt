package com.kottland.ozusnews.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

import com.kottland.ozusnews.data.model.NewsApiResponse

interface NewsApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("category") category: String? = null,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20,
        @Query("apiKey") apiKey: String
    ): Response<NewsApiResponse>
}