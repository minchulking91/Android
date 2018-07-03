package com.example.overseas_football.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.overseas_football.R
import com.example.overseas_football.model.Articles
import com.example.overseas_football.model.NewsResModel
import kotlinx.android.synthetic.main.news_view.view.*

class NewsAdapter(var context: Context, var list: ArrayList<Articles>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.news_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bind(position: Int) {
            itemView.textview_title.text = list[position].title
            itemView.textview_description.text = list[position].description
        }
    }
}