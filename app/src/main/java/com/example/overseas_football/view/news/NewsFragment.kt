package com.example.overseas_football.view.news

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.overseas_football.R
import com.example.overseas_football.databinding.Tab2Binding
import com.example.overseas_football.view.BaseFragment
import kotlinx.android.synthetic.main.tab2.*

class NewsFragment : BaseFragment() {
    private val newsVM: NewsVM by lazy {
        ViewModelProviders.of(this).get(NewsVM::class.java)
    }

    @LayoutRes
    private val layoutResource = R.layout.tab2
    private lateinit var binding: Tab2Binding
    private val newsAdapter: NewsAdapter = NewsAdapter {
        it?.let {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.url)))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResource, container, false)
        binding.tab2viewmodel = newsVM

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setupViews
        setupViews()
        subscribeUI(newsVM)
    }

    private fun setupViews() {
        swipelayout.setOnRefreshListener {
            newsVM.loadNews()
        }
        news_recyclerview.adapter = newsAdapter
    }

    private fun subscribeUI(newsVM: NewsVM) {
        newsVM.newsList.observe(this, Observer {
            //present to view
            swipelayout.isRefreshing = false
            if (it != null) {
                when {
                    it.isSuccess -> {
                        newsAdapter.setList(it.data ?: emptyList())
                        progressbar.visibility = View.GONE
                        news_recyclerview.visibility = View.VISIBLE
                    }
                    it.isError -> {
                        //TODO show error view
                        newsAdapter.setList(emptyList())
                        progressbar.visibility = View.GONE
                        news_recyclerview.visibility = View.GONE
                    }
                    it.isLoading -> {
                        news_recyclerview.visibility = View.GONE
                        progressbar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        newsVM.loadNews()
    }
}