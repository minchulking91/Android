package com.example.overseas_football.view.utill

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar

open class Utill{
    open fun startProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
    }

    open fun finishProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.GONE
    }
}