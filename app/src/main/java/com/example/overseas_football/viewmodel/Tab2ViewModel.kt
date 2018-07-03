package com.example.overseas_football.viewmodel

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.example.overseas_football.R
import com.example.overseas_football.model.Contents
import com.example.overseas_football.model.NewsResModel
import com.example.overseas_football.network.Constants
import com.example.overseas_football.network.RetrofitClient
import com.example.overseas_football.view.adapter.NewsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.tab2.view.*
import org.json.JSONArray

class Tab2ViewModel(var context: Context) {

    fun getNews(view: View) {
        RetrofitClient()
                .setRetrofit(Constants.NEWS_URL)
                .getNews("kr", "sports", context.getString(R.string.news_apikey))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            if (it.totalResults == 20) {
                                val jsonArray = JSONArray(it.contents)
                                val news_list: ArrayList<NewsResModel> = ArrayList()
                                val contents_list: ArrayList<Contents> = ArrayList()
                                for (x in 0 until jsonArray.length()) {
                                    val jsonObject = jsonArray.getJSONObject(x)

                                    val author = jsonObject.getString("author")
                                    val status = jsonObject.getString("status")
                                    val totalResults = jsonObject.getInt("totalResults")
                                    val title = jsonObject.getString("title")
                                    val description = jsonObject.getString("description")
                                    val url = jsonObject.getString("url")
                                    val urlToImage = jsonObject.getString("urlToImage")
                                    val publishedAt = jsonObject.getString("publishedAt")
                                    contents_list.add(Contents(author, title, description, url, urlToImage, publishedAt, null))
                                    news_list.add(NewsResModel(status, totalResults, contents_list))
                                }
                                Log.e("확인", contents_list.size.toString())
                                view.news_recyclerview.layoutManager = LinearLayoutManager(context)
                                view.news_recyclerview.adapter = NewsAdapter(context, news_list)
                            } else {
                                Log.e("확인", "123123")
                            }
                        },
                        onError = {
                            Log.e("zzzz", it.message)
                        }
                )
    }

}