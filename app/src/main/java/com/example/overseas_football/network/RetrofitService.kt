package com.example.overseas_football.network

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitService{
    @FormUrlEncoded
    @POST("/signup")
    fun signup(@Field("email") email:String,
               @Field("nickname") nickname:String,
               @Field("division") division:String): Observable<HashMap<String, String>>
}

