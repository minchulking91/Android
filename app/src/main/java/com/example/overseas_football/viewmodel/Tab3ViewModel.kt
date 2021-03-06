package com.example.overseas_football.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.overseas_football.model.BasicResModel
import com.example.overseas_football.network.Constants
import com.example.overseas_football.network.RetrofitClient
import com.example.overseas_football.view.utill.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody

class Tab3ViewModel : BaseViewModel() {
    val basicResModel: MutableLiveData<BasicResModel> = MutableLiveData()

    fun setProfileImage(imagefile: MultipartBody.Part, email: RequestBody) {
        RetrofitClient()
                .setRetrofit(Constants.BASE_URL)
                .setProfileImage(imagefile, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            basicResModel.value = it
                        },
                        onError = {
                            Log.e("wrwer", it.message)
                        }
                )
    }
}