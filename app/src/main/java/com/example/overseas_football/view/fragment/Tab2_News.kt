package com.example.overseas_football.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.overseas_football.R
import com.example.overseas_football.databinding.Tab2Binding
import com.example.overseas_football.view.BaseFragment
import com.example.overseas_football.view.adapter.NewsAdapter
import com.example.overseas_football.viewmodel.Tab2ViewModel
import kotlinx.android.synthetic.main.tab2.*
import kotlinx.android.synthetic.main.tab2.view.*

class Tab2_News : BaseFragment() {
    private val viewmodel: Tab2ViewModel by lazy {
        ViewModelProviders
                .of(this@Tab2_News)
                .get(Tab2ViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (DataBindingUtil.inflate(inflater, R.layout.tab2, container, false) as Tab2Binding).let {
            with(it) {
                tab2viewmodel = viewmodel
                val apiKey = requireContext().getString(R.string.news_apikey)
                root.swipelayout.isRefreshing = true
                viewmodel.getNews(apiKey)
                root.swipelayout.setOnRefreshListener {
                    viewmodel.getNews(apiKey)
                }
                observerNews()
                return root
            }
        }
    }

    private fun observerNews() {
        viewmodel.newsList.observe(this, Observer {
            swipelayout.isRefreshing = false
            if (it != null) {
                news_recyclerview.layoutManager = LinearLayoutManager(context)
                news_recyclerview.adapter = NewsAdapter(requireContext(), it)
            } else {

            }
        })

    }
}