package com.example.overseas_football.view.fragment

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.overseas_football.R
import com.example.overseas_football.databinding.Tab3Binding
import com.example.overseas_football.network.Constants
import com.example.overseas_football.view.BaseFragment
import com.example.overseas_football.view.utill.CustomViewModelFactory
import com.example.overseas_football.view.utill.Shared
import com.example.overseas_football.viewmodel.Tab2ViewModel
import com.example.overseas_football.viewmodel.Tab3ViewModel
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.tab3.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class Tab3_MyProfile : BaseFragment() {
    private val viewmodel: Tab3ViewModel by lazy {
        ViewModelProviders.of(this@Tab3_MyProfile,
                ViewModelFactory(requireActivity(), requireContext(), this))
                .get(Tab3ViewModel::class.java)
    }

    inner class ViewModelFactory(private val activity: Activity,
                                 private val context: Context,
                                 private val fragment: Fragment) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return Tab3ViewModel(activity, context, fragment as Tab3_MyProfile) as T
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (DataBindingUtil.inflate(inflater, R.layout.tab3, container, false) as Tab3Binding).let {
            with(it) {
                setLifecycleOwner(this@Tab3_MyProfile)
                tab3ViewModel = viewmodel
                return root
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewmodel.user.observe(this, Observer { user ->
            if (user != null) {
                tv_nickname.text = user.nickname

                linearLayout_Login.visibility = View.VISIBLE
                linearLayout_beLogin.visibility = View.GONE
                //사용자 프로필 이미지 유무
                val loadValue: Any
                if (user.img != null) {
                    circleimgview_profile.background = null
                    loadValue = Constants.BASE_URL + "glideProfile?img=" + user.img
                } else {
                    loadValue = R.drawable.defalut_profile_img
                }
                Glide.with(requireActivity())
                        .load(loadValue)
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                        .apply(RequestOptions.skipMemoryCacheOf(true))
                        .into(circleimgview_profile)
                tv_nickname.text = user.nickname
            } else {
                linearLayout_Login.visibility = View.GONE
                linearLayout_beLogin.visibility = View.VISIBLE
            }
        })

    }

    override fun onResume() {
        super.onResume()
        viewmodel.checkLogin()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val file = File(result.uri.path)
                val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
                val fileBody = MultipartBody.Part.createFormData("profileImage", file.name, requestFile)
                val requestEmail = RequestBody.create(MultipartBody.FORM, Shared().getUser(context!!)!!.email)
                viewmodel.setProfileImage(fileBody, requestEmail)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}