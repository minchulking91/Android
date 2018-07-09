package com.example.overseas_football.view.utill

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.afollestad.materialdialogs.MaterialDialog

open class Utill {
    open fun openBasicDialog(context: Context, title: String, content: String): MaterialDialog.Builder {
        return MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .negativeText("취소")
                .positiveText("확인")
    }
    // 개인정보 화면
    open fun checkLoginView(context: Context, linearLayout_Login: LinearLayout, linearLayout_beLogin: LinearLayout) {
        if (Shared().getUser(context) != null) {
            linearLayout_Login.visibility = View.VISIBLE
            linearLayout_beLogin.visibility = View.GONE
        } else {
            linearLayout_Login.visibility = View.GONE
            linearLayout_beLogin.visibility = View.VISIBLE
        }
    }

    //해축갤
    open fun checkLoginView(context: Context, linearLayout_beLogin: LinearLayout) {
        if (Shared().getUser(context) != null) {
            linearLayout_beLogin.visibility = View.GONE
        } else {
            linearLayout_beLogin.visibility = View.VISIBLE
        }
    }
}