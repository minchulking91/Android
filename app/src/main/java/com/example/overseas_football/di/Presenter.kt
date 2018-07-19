package com.example.overseas_football.di

import android.arch.lifecycle.ViewModel
import com.example.overseas_football.model.NewsResModel
import com.example.overseas_football.network.ApiManager
import io.reactivex.Observable

class Presenter(val apiManager: ApiManager) : ViewModel() {
    fun getNewscountry(country: String, categoty: String, apiKey: String): Observable<NewsResModel> = apiManager.getNews(country, categoty, apiKey)
}