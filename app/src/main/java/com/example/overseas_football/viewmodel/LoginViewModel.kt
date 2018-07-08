package com.example.overseas_football.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import com.example.overseas_football.R
import com.example.overseas_football.network.Constants
import com.example.overseas_football.network.RetrofitClient
import com.example.overseas_football.view.MainActivity
import com.example.overseas_football.view.utill.Shared
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(var activity: Activity) {
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
                RetrofitClient()
                        .setRetrofit(Constants.BASE_URL)
                        .setResister(user.email!!, user.displayName!!, "google")
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            Log.e("log", it.result)
                            if (it.result == "success") {
                                Log.e("user",it.User.toString())
                                Shared().saveUser(activity, it.User)
                                activity.finish()
                            }
                        }
            }
        }
    }
}