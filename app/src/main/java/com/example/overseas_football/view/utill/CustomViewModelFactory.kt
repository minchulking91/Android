package com.example.overseas_football.view.utill

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.Fragment
import com.example.overseas_football.view.fragment.Tab1_Community
import com.example.overseas_football.view.fragment.Tab2_News
import com.example.overseas_football.view.fragment.Tab3_MyProfile
import com.example.overseas_football.viewmodel.Tab1ViewModel
import com.example.overseas_football.viewmodel.Tab2ViewModel
import com.example.overseas_football.viewmodel.Tab3ViewModel

class CustomViewModelFactory(private val activity: Activity, val fragment: Fragment) : ViewModelProvider.NewInstanceFactory() {
    val tab1_Community = Tab1_Community()
    val tab2_News = Tab2_News()
    val tab3_MyProfile = Tab3_MyProfile()
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            tab1_Community==fragment -> Tab1ViewModel(activity) as T
            tab2_News==fragment -> Tab2ViewModel(activity) as T
            else -> Tab3ViewModel(activity, activity, tab3_MyProfile) as T
        }
    }
}