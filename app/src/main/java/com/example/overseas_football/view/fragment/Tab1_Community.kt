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
import com.example.overseas_football.databinding.Tab1Binding
import com.example.overseas_football.view.BaseFragment
import com.example.overseas_football.viewmodel.Tab1ViewModel

class Tab1_Community : BaseFragment() {
    private val viewmodel: Tab1ViewModel by lazy {
        ViewModelProviders.of(this@Tab1_Community,ViewModelFactory(requireActivity())).get(Tab1ViewModel::class.java)
    }

   inner class ViewModelFactory(private val activity:Activity ) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
           return Tab1ViewModel(activity) as T
        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (DataBindingUtil.inflate(inflater, R.layout.tab1, container, false) as Tab1Binding).let {
            with(it) {
                setLifecycleOwner(this@Tab1_Community)
                tab1ViewModel = viewmodel
                return root
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewmodel.CheckLogin(requireContext())
    }
}
