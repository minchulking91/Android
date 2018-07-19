package com.example.overseas_football.view.news

import android.support.v7.util.DiffUtil
import com.example.overseas_football.entity.Article

class NewsDiffCallback(private val oldList: List<Article>, private val newList: List<Article>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.url == newItem.url
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        //TODO 적절한 비교 수행하기
        return oldItem.url == newItem.url
    }
}