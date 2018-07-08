package com.example.overseas_football.viewmodel

import android.app.Activity
import android.view.View
import android.widget.Toast
import com.example.overseas_football.view.utill.Shared
import com.google.firebase.auth.FirebaseAuth

class Tab3ViewModel(var activity: Activity) {
    fun onclick(view: View) {
        if (FirebaseAuth.getInstance().currentUser != null) {
            FirebaseAuth.getInstance().signOut()
        }
        Shared().removeUser(activity)
        Toast.makeText(activity, "정상적으로 로그인 되었습니다.", Toast.LENGTH_LONG).show()
    }
}