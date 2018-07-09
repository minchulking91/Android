package com.example.overseas_football.view

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.include_toolbar.*

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        toolbar_img_back.setOnClickListener {
            super.finish()
        }
    }

    open fun setToolbarTile(activity: Activity, title: String) {
        activity.toolbar_txt_title.text = title
    }
}