package com.example.overseas_football.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.overseas_football.R
import com.example.overseas_football.databinding.ActivityMainBinding
import com.example.overseas_football.view.fragment.Tab1_Community
import com.example.overseas_football.view.fragment.Tab2_News
import com.example.overseas_football.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val mainViewModel=MainViewModel()
        binding.mainActivity = mainViewModel

        binding.bottombar.setOnTabSelectListener { tabId ->
            val transaction = fragmentManager.beginTransaction()
            when (tabId) {
                R.id.tab1_team -> transaction.replace(R.id.framelayout, Tab1_Community()).commit()
                R.id.tab2_match -> transaction.replace(R.id.framelayout, Tab2_News()).commit()
            }
        }
    }
}
