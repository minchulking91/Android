package com.example.overseas_football.view.utill

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog

open class Utill {
    open fun openBasicDialog(context: Context, title: String, content: String): MaterialDialog.Builder {
        return MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .negativeText("취소")
                .positiveText("확인")
    }
}