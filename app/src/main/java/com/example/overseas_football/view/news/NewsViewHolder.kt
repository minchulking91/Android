package com.example.overseas_football.view.news

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.overseas_football.R
import com.example.overseas_football.entity.Article
import kotlinx.android.synthetic.main.news_view.view.*

class NewsViewHolder(view: View, onClick: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
    init {
        itemView.setOnClickListener {
            onClick.invoke(adapterPosition)
        }
    }

    fun bind(article: Article?) {
        if (article == null) {
            //TODO set view with place holder
            //TODO cancel glide request
            return
        }
        itemView.textview_title.text = article.title
        itemView.textview_description.text = article.description
        Glide.with(itemView.context).load(article.urlToImage).into(itemView.imageview).view.scaleType = ImageView.ScaleType.FIT_XY

    }

    companion object {
        fun newInstance(parent: ViewGroup, onClick: (Int) -> Unit): NewsViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.news_view, parent, false)
            return NewsViewHolder(view, onClick)
        }
    }
}