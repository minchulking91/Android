package com.example.overseas_football.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.overseas_football.R
import com.example.overseas_football.model.User
import com.example.overseas_football.network.Constants
import com.example.overseas_football.network.RetrofitClient
import com.example.overseas_football.view.fragment.Tab3_MyProfile
import com.example.overseas_football.view.utill.Shared
import com.example.overseas_football.view.utill.Utill
import com.google.firebase.auth.FirebaseAuth
import com.kakao.auth.Session
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import de.hdodenhof.circleimageview.CircleImageView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody

class Tab3ViewModel(var activity: Activity, var context: Context, var tab3_MyProfile: Tab3_MyProfile) : Utill() {
    val tv_nickname: ObservableField<String> by lazy { ObservableField<String>() }
    fun setProfileImage() {
        CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(context, tab3_MyProfile)
    }

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
            //사용자 프로필 이미지 유무
            if (user.img.isNotEmpty()) {
                activity.findViewById<CircleImageView>(R.id.circleimgview_profile).background=null
                Glide.with(activity)
                        .load(Constants.BASE_URL + "glideProfile?img=" + user.img)
                        .into(activity.findViewById<CircleImageView>(R.id.circleimgview_profile))
            } else {
                activity.findViewById<CircleImageView>(R.id.circleimgview_profile).visibility = View.VISIBLE
            }
        } else {
            activity.findViewById<LinearLayout>(R.id.linearLayout_Login).visibility = View.GONE
            activity.findViewById<LinearLayout>(R.id.linearLayout_beLogin).visibility = View.VISIBLE
        }
    }

    fun setProfileImage(imagefile: MultipartBody.Part, email: RequestBody) {
        RetrofitClient()
                .setRetrofit(Constants.BASE_URL)
                .setProfileImage(imagefile, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            val user = Shared().getUser(context)
                            if (user != null) {
                                user.img = it.message
                                val circleImgviewProfile: CircleImageView = activity.findViewById(R.id.circleimgview_profile)
                                circleImgviewProfile.visibility = View.GONE
                                Glide.with(activity)
                                        .load(Constants.BASE_URL + "glideProfile?img=" + it.message)
                                        .into(circleImgviewProfile)
                            }
                        },
                        onError = { Log.e("wrwer", it.message) }
                )
    }
}