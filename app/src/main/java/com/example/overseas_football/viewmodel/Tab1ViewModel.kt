package com.example.overseas_football.viewmodel

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.util.Log
import android.view.View
import com.example.overseas_football.view.LoginActivity

class Tab1ViewModel(val activity: Activity) : ViewModel() {

    fun LoginActivity(view: View) {
        Log.e("??","??")
        activity.startActivity(Intent(activity, LoginActivity::class.java))
    }
}