package com.example.overseas_football.entity

data class Article(
        val author: Any,
        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String,
        val publishedAt: String,
        val source: Source? = null
)
