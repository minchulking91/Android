package com.example.overseas_football.view

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.example.overseas_football.model.User
import com.example.overseas_football.view.utill.Shared
import kotlinx.android.synthetic.main.include_toolbar.*

open class BaseActivity : AppCompatActivity() {
    val shared=com.example.overseas_football.view.utill.Shared()
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        toolbar_img_back.setOnClickListener {
            super.finish()
        }
    }

    open fun setToolbarTile(activity: Activity, title: String) {
        activity.toolbar_txt_title.text = title
    }
    open fun isExistUser(): Boolean {
        return shared.getUser(this)!=null
    }
}