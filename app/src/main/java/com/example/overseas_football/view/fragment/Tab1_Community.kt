package com.example.overseas_football.view.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.overseas_football.R
import com.example.overseas_football.databinding.Tab1Binding
import com.example.overseas_football.view.BaseFragment
import com.example.overseas_football.viewmodel.Tab1ViewModel

class Tab1_Community : BaseFragment() {
    lateinit var tab1: Tab1ViewModel
    lateinit var binding: Tab1Binding
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater!!, R.layout.tab1, container, false)
        tab1 = Tab1ViewModel(activity)

        binding.tab1ViewModel = tab1
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        checkLoginView(context, binding.linBelogin)
        tab1.CheckLogin(context)
    }
}
