package com.example.overseas_football.data.news

import com.example.overseas_football.data.news.model.NewsDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("/v2/top-headlines")
    fun getNews(@Query("country") country: String,
                @Query("category") category: String,
                @Query("apiKey") apiKey: String): Single<NewsDTO>
}