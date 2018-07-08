package com.example.overseas_football.view

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.overseas_football.R
import com.example.overseas_football.databinding.ActivityMainBinding
import com.example.overseas_football.model.User
import com.example.overseas_football.network.Constants
import com.example.overseas_football.view.fragment.Tab1_Community
import com.example.overseas_football.view.fragment.Tab2_News
import com.example.overseas_football.view.fragment.Tab3_MyProfile
import com.example.overseas_football.view.utill.Shared
import com.example.overseas_football.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    val mainViewModel = MainViewModel()

    val tab1_Community = Tab1_Community()
    val tab2_News = Tab2_News()
    val tab3_MyProfile = Tab3_MyProfile()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.mainActivity = mainViewModel

        binding.bottombar.setOnTabSelectListener { tabId ->
            val transaction = fragmentManager.beginTransaction()
            when (tabId) {
                R.id.tab1_community -> transaction.replace(R.id.framelayout, tab1_Community).commit()
                R.id.tab2_news -> transaction.replace(R.id.framelayout, tab2_News).commit()
                R.id.tab3_myprofile -> transaction.replace(R.id.framelayout, tab3_MyProfile).commit()
            }
        }
    }
}
