package com.example.overseas_football.network

import com.example.overseas_football.model.BasicResModel
import com.example.overseas_football.model.NewsResModel
import com.example.overseas_football.model.UserLoginResModel
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface OverseasApi {
    fun setResister(email: String, nickname: String, division: String): Observable<UserLoginResModel>

    fun getNews(country: String, categoty: String, apiKey: String): Observable<NewsResModel>

    fun setProfileImage(profileImage: MultipartBody.Part, email: RequestBody): Observable<BasicResModel>
}