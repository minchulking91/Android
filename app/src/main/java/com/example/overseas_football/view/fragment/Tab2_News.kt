package com.example.overseas_football.view.fragment

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.overseas_football.R
import com.example.overseas_football.databinding.Tab2Binding
import com.example.overseas_football.view.BaseFragment
import com.example.overseas_football.view.utill.CustomViewModelFactory
import com.example.overseas_football.viewmodel.Tab1ViewModel
import com.example.overseas_football.viewmodel.Tab2ViewModel

class Tab2_News : BaseFragment() {
    private val viewmodel: Tab2ViewModel by lazy {
        ViewModelProviders.of(this@Tab2_News, ViewModelFactory(requireActivity())).get(Tab2ViewModel::class.java)
    }
    inner class ViewModelFactory(private val activity: Activity) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return Tab2ViewModel(activity) as T
        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (DataBindingUtil.inflate(inflater, R.layout.tab2, container, false) as Tab2Binding).let {
            with(it) {
                tab2viewmodel = viewmodel
                viewmodel.getNews(root)
                swipelayout.setOnRefreshListener {
                    viewmodel.getNews(root)
                }
                return root
            }
        }
    }
}