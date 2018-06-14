package com.example.overseas_football.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.view.View
import com.example.overseas_football.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : BaseObservable() {
    val textview_result: ObservableField<String> by lazy { ObservableField<String>() }
    fun GetGoogleSignInClient(context: Context): GoogleSignInClient {
        return GoogleSignIn.getClient(context, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build())
    }

    fun GoogleSessionCheck() {
        textview_result.let {
            val user = FirebaseAuth.getInstance().currentUser
            if (null == user) {
                it.set("비로그인")
            } else {
                it.set(user.displayName)
            }
        }
    }

    fun GoogleLogOut(view: View) {
        FirebaseAuth.getInstance().signOut()
        textview_result.set("비로그인")
    }
}