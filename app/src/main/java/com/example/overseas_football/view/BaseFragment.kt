package com.example.overseas_football.view

import android.app.Fragment
import android.view.View
import android.widget.ProgressBar

open class BaseFragment : Fragment() {

    open fun startProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
    }

    open fun finishProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.GONE
    }
}