package com.example.overseas_football.view.utill

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import com.afollestad.materialdialogs.MaterialDialog

open class Utill {
    open fun openBasicDialog(context: Context, title: String, content: String): MaterialDialog.Builder {
        return MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .negativeText("취소")
                .positiveText("확인")
    }

    open fun startProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
    }

    open fun finishProgessBar(progressBar: ProgressBar) {
        progressBar.visibility = View.GONE
    }
}