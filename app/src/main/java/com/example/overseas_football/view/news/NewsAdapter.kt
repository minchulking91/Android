package com.example.overseas_football.view.news

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.example.overseas_football.entity.Article
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class NewsAdapter(private val onClick: (Article?) -> Unit) : RecyclerView.Adapter<NewsViewHolder>() {
    private var _list: List<Article> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.newInstance(parent) {
            val article: Article? = try {
                _list[it]
            } catch (e: Exception) {
                null
            }
            onClick.invoke(article)
        }
    }

    override fun getItemCount(): Int {
        return _list.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(_list[position])
    }

    private var diffUtilDisposable: Disposable? = null

    fun setList(list: List<Article>?) {
        diffUtilDisposable?.dispose()
        diffUtilDisposable = Single
                .fromCallable {
                    val newList = list ?: emptyList()
                    val callback = NewsDiffCallback(_list, newList)
                    val result = DiffUtil.calculateDiff(callback)
                    result to newList
                }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result, throwable ->
                    if (throwable != null) {
                        Log.e("NewsAdapter", "", throwable)
                    } else {
                        val diffUtilResult = result.first
                        val newList = result.second
                        _list = newList
                        diffUtilResult.dispatchUpdatesTo(this)
                    }
                }
    }
}