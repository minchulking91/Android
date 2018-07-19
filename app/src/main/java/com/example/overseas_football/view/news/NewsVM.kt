package com.example.overseas_football.view.news

import android.arch.lifecycle.MutableLiveData
import com.example.overseas_football.data.Resource
import com.example.overseas_football.data.news.NewsApiManager
import com.example.overseas_football.entity.Article
import com.example.overseas_football.view.utill.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NewsVM : BaseViewModel() {
    val newsList: MutableLiveData<Resource<List<Article>>> = MutableLiveData()
    private val newsApiManager = NewsApiManager()
    private var newsDisposable: Disposable? = null

    fun loadNews() {
        newsDisposable?.dispose()
        newsList.value = Resource.loading(null)
        newsDisposable = newsApiManager.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result, throwable ->
                    if (throwable != null) {
                        newsList.value = Resource.error(throwable, null)
                    } else {
                        newsList.value = Resource.success(result)
                    }
                }
    }

    override fun onCleared() {
        super.onCleared()
        newsDisposable?.dispose()
    }
}
