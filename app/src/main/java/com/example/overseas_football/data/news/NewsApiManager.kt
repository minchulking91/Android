package com.example.overseas_football.data.news

import com.example.overseas_football.BuildConfig
import com.example.overseas_football.entity.Article
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiManager {
    private val newsService: NewsApiService

    init {
        newsService = checkNotNull(buildRetrofit())
    }

    private fun buildRetrofit(): NewsApiService? {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient
                .Builder()
                .addInterceptor(logging)
                .build()

        val retrofitApi = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.NEWS_BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofitApi.create(NewsApiService::class.java)
    }

    fun getNews(): Single<List<Article>> {
        return newsService
                .getNews("kr", "sports", BuildConfig.NEWS_API_KEY)
                .map {
                    if (it.isSuccess) {
                        it.articles
                    } else {
                        throw IllegalStateException("Error from new API /top-headlines")
                    }
                }
    }
}