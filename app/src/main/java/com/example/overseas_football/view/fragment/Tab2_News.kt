package com.example.overseas_football.view.fragment

import android.app.Fragment
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.overseas_football.R
import com.example.overseas_football.databinding.Tab2Binding
import com.example.overseas_football.viewmodel.Tab2ViewModel

class Tab2_News : Fragment() {
    lateinit var tab2: Tab2ViewModel
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: Tab2Binding = DataBindingUtil.inflate(inflater!!, R.layout.tab2, container, false)
        tab2 = Tab2ViewModel(context)
        binding.tab2viewmodel = tab2
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        tab2.getNews(view)
    }
}