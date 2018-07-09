package com.example.overseas_football.viewmodel

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import com.example.overseas_football.R
import com.example.overseas_football.network.Constants
import com.example.overseas_football.network.RetrofitClient
import com.example.overseas_football.view.LoginActivity
import com.example.overseas_football.view.SignupActivity
import com.example.overseas_football.view.utill.Shared
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.UserManagement
import com.kakao.util.helper.log.Logger
import java.util.ArrayList


class LoginViewModel(var activity: Activity) {
    val textview_result: ObservableField<String> by lazy { ObservableField<String>() }

    init {
//        (activity as LoginActivity).setToolbarTile(activity,"로그인")
    }

    fun GetGoogleSignInClient(context: Context): GoogleSignInClient {
        return GoogleSignIn.getClient(context, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build())
    }

    fun GoogleLogin() {
        textview_result.let {
            val user = FirebaseAuth.getInstance().currentUser
            if (null == user) {
                it.set("비로그인")
            } else {
                Log.e("url", user.photoUrl.toString())
                RetrofitClient()
                        .setRetrofit(Constants.BASE_URL)
                        .setResister(user.email!!, user.displayName!!, "google")
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            Log.e("log", it.result)
                            if (it.result == "success") {
                                Log.e("user", it.User.toString())
                                Shared().saveUser(activity, it.User)
                                activity.finish()
                            }
                        }
            }
        }
    }

     fun requestKakaoAuth() {
        val keys = ArrayList<String>()
        keys.add("properties.nickname")
        keys.add("properties.profile_image")
        keys.add("kakao_account.email")

        UserManagement.getInstance().me(keys, object : MeV2ResponseCallback() {
            override fun onFailure(errorResult: ErrorResult?) {
                val message = "failed to get user info. msg=" + errorResult!!
                Logger.d(message)
            }

            override fun onSessionClosed(errorResult: ErrorResult) {

            }

            override fun onSuccess(response: MeV2Response) {
                Log.e("user nickname : ",""+ response.nickname)
                Log.e("email: " ,""+  response.kakaoAccount.email)
                Log.e("profile image: " ,""+  response.profileImagePath)

            }

        })
    }
    fun SignUpActivity(view: View) {
        activity.startActivityForResult(Intent(activity, SignupActivity::class.java), RESULT_OK)
    }
}