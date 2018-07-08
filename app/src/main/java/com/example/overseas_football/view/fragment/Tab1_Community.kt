package com.example.overseas_football.view.fragment

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.overseas_football.R
import com.example.overseas_football.databinding.Tab1Binding
import com.example.overseas_football.view.BaseFragment
import com.example.overseas_football.viewmodel.Tab1ViewModel

class Tab1_Community : BaseFragment() {
    lateinit var tab1: Tab1ViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (DataBindingUtil.inflate(inflater!!, R.layout.tab1, container, false) as Tab1Binding).let {
            tab1=Tab1ViewModel(activity)
            it.tab1ViewModel = tab1
            return it.root
        }
    }

    override fun onResume() {
        super.onResume()
        Log.e("onresume","resume")
        tab1.CheckLogin(context)
    }
}
