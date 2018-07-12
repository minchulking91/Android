package com.example.overseas_football.view.fragment

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.overseas_football.R
import com.example.overseas_football.databinding.Tab3Binding
import com.example.overseas_football.network.Constants
import com.example.overseas_football.network.RetrofitClient
import com.example.overseas_football.view.BaseFragment
import com.example.overseas_football.view.utill.Shared
import com.example.overseas_football.viewmodel.Tab3ViewModel
import com.theartofdev.edmodo.cropper.CropImage
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import com.kakao.auth.StringSet.file
import com.kakao.auth.StringSet.file


class Tab3_MyProfile : BaseFragment() {
    lateinit var tab3: Tab3ViewModel
    lateinit var binding: Tab3Binding
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater!!, R.layout.tab3, container, false)
        tab3 = Tab3ViewModel(activity, context, this)
        binding.tab3ViewModel = tab3
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        tab3.checkLogin()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val file = File(result.uri.path)
                val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
                val fileBody = MultipartBody.Part.createFormData("profileImage", file.name, requestFile)
                val requestEmail = RequestBody.create(MultipartBody.FORM, Shared().getUser(context)!!.email)
                tab3.setProfileImage(fileBody, requestEmail)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}