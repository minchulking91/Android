package com.example.overseas_football.view.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.overseas_football.R
import com.example.overseas_football.databinding.Tab3Binding
import com.example.overseas_football.view.BaseFragment
import com.example.overseas_football.viewmodel.Tab3ViewModel

class Tab3_MyProfile : BaseFragment() {
    lateinit var tab3: Tab3ViewModel
    lateinit var binding: Tab3Binding
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater!!, R.layout.tab3, container, false)
        tab3 = Tab3ViewModel(activity)
        binding.tab3ViewModel = tab3
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        checkLoginView(context, binding.linearLayoutLogin, binding.linearLayoutBeLogin)
    }
}