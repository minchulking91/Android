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
import com.example.overseas_football.view.utill.Utill
import com.github.clans.fab.FloatingActionMenu
import com.google.firebase.auth.FirebaseAuth

class Tab1ViewModel(val activity: Activity) : ViewModel() {
    val user: ObservableParcelable<User> = ObservableParcelable()
    fun LoginActivity(view: View) {
        Log.e("??", "??")
        activity.startActivity(Intent(activity, LoginActivity::class.java))
    }

    fun CheckLogin(context: Context) {
        if (Shared().getUser(context) != null) {
            activity.findViewById<LinearLayout>(R.id.lin_belogin).visibility = View.GONE
            activity.findViewById<FloatingActionMenu>(R.id.floating_menu_button).visibility = View.VISIBLE
        } else {
            activity.findViewById<LinearLayout>(R.id.lin_belogin).visibility = View.VISIBLE
            activity.findViewById<FloatingActionMenu>(R.id.floating_menu_button).visibility = View.GONE
        }
    }
}