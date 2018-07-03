package com.example.overseas_football.network

import com.example.overseas_football.model.NewsResModel
import io.reactivex.Observable
import retrofit2.http.*

interface RetrofitService {
    @FormUrlEncoded
    @POST("/signup")
    fun setResister(@Field("email") email: String,
                    @Field("nickname") nickname: String,
                    @Field("division") division: String): Observable<HashMap<String, String>>

    @GET("/v2/top-headlines")
    fun getNews(@Query("country") country: String,
                @Query("categoty") categoty: String,
                @Query("apiKey") apiKey: String): Observable<NewsResModel>
}

