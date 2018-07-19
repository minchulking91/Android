package com.example.overseas_football.data.news.model

import com.example.overseas_football.entity.Article

data class NewsDTO(
        val status: String,
        val totalResults: Int,
        val articles: List<Article>
) {
    val isSuccess: Boolean
        get() = status == "ok"

}