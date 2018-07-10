package com.example.overseas_football.viewmodel

import android.app.Activity
import android.content.Intent
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.example.overseas_football.R
import com.example.overseas_football.model.User
import com.example.overseas_football.view.utill.Shared
import com.example.overseas_football.view.utill.Utill
import com.google.firebase.auth.FirebaseAuth
import com.kakao.auth.Session
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback

class Tab3ViewModel(var activity: Activity) : Utill() {
    val tv_nickname: ObservableField<String> by lazy { ObservableField<String>() }
    fun LoginActivity(view: View) {
        activity.startActivity(Intent(activity, com.example.overseas_football.view.LoginActivity::class.java))
    }

    fun onclick(view: View) {
        openBasicDialog(activity, "로그아웃", "로그아웃 하시겠습니까?")
                .onPositive { dialog, which ->
                    if (FirebaseAuth.getInstance().currentUser != null) {
                        FirebaseAuth.getInstance().signOut()
                    }
                    if (Session.getCurrentSession().isOpened) {
                        UserManagement.getInstance().requestLogout(object : LogoutResponseCallback() {
                            override fun onCompleteLogout() {
                                Log.e("kakao", "logout")
                            }

                        })
                    }
                    Shared().removeUser(activity)
                    checkLogin()
                    Toast.makeText(activity, "정상적으로 로그인 되었습니다.", Toast.LENGTH_LONG).show()
                }.show()
    }

    fun checkLogin() {
        val user: User? = Shared().getUser(activity)
        if (user != null) {
            tv_nickname.set(user.nickname)
            activity.findViewById<LinearLayout>(R.id.linearLayout_Login).visibility = View.VISIBLE
            activity.findViewById<LinearLayout>(R.id.linearLayout_beLogin).visibility = View.GONE
        } else {
            activity.findViewById<LinearLayout>(R.id.linearLayout_Login).visibility = View.GONE
            activity.findViewById<LinearLayout>(R.id.linearLayout_beLogin).visibility = View.VISIBLE
        }
    }
}