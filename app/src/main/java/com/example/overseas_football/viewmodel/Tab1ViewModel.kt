package com.example.overseas_football.viewmodel

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.databinding.ObservableParcelable
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.example.overseas_football.R
import com.example.overseas_football.model.User
import com.example.overseas_football.network.Constants
import com.example.overseas_football.view.LoginActivity
import com.example.overseas_football.view.utill.Shared
import com.google.firebase.auth.FirebaseAuth

class Tab1ViewModel(val activity: Activity) : ViewModel() {
    val user: ObservableParcelable<User> = ObservableParcelable()
    fun LoginActivity(view: View) {
        Log.e("??", "??")
        activity.startActivityForResult(Intent(activity, LoginActivity::class.java), Constants.LOGIN_RESULT_OK)
    }

    fun FirebaseLogout(view: View) {
        FirebaseAuth.getInstance().signOut()
    }

    fun CheckLogin(context: Context) {
        Log.e("onresume","sum")
        if (Shared().getUser(context) != null) {
            Log.e("onresume","sum")
            activity.findViewById<LinearLayout>(R.id.lin_belogin).visibility = View.GONE
        } else {
            Log.e("onresume","sum111111")
            activity.findViewById<LinearLayout>(R.id.lin_belogin).visibility = View.VISIBLE
        }
    }
}