package com.example.overseas_football.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.example.overseas_football.R
import com.example.overseas_football.model.Articles
import com.example.overseas_football.network.Constants
import com.example.overseas_football.network.RetrofitClient
import com.example.overseas_football.view.adapter.NewsAdapter
import com.example.overseas_football.view.utill.BaseViewModel
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.tab2.view.*
import org.json.JSONArray

class Tab2ViewModel(var context: Context) : BaseViewModel(){

    fun getNews(view: View) {
        view.swipelayout.isRefreshing = true
        RetrofitClient()
                .setRetrofit(Constants.NEWS_URL)
                .getNews("kr", "sports", context.getString(R.string.news_apikey))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            view.swipelayout.isRefreshing = false
                            if (it.totalResults == 20) {
                                val jsonArray = JSONArray(Gson().toJson(it.articles))
                                val contents_list: ArrayList<Articles> = ArrayList()
                                for (x in 0 until jsonArray.length()) {
                                    val jsonObject = jsonArray.getJSONObject(x)

                                    val author = jsonObject.optString("author", "null")
                                    val title = jsonObject.optString("title", "null")
                                    val description = jsonObject.optString("description", "null")
                                    val url = jsonObject.optString("url", "null")
                                    val urlToImage = jsonObject.optString("urlToImage", "null")
                                    val publishedAt = jsonObject.optString("publishedAt", "null")
                                    contents_list.add(Articles(author, title, description, url, urlToImage, publishedAt, null))
                                }
                                view.news_recyclerview.layoutManager = LinearLayoutManager(context)
                                view.news_recyclerview.adapter = NewsAdapter(context, contents_list)
                            } else {
                                Log.e("확인", "123123")
                            }
                        },
                        onError = {
                            view.swipelayout.isRefreshing = false
                            Log.e("zzzz", it.message)
                        }
                )
    }

}