package com.example.overseas_football.view.fragment

import android.app.Fragment
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.overseas_football.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.tab1.*
import kotlinx.android.synthetic.main.tab1.view.*

class Tab1_Community : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =inflater!!.inflate(R.layout.tab1, container, false)
        view.button_logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
        }
        return view
    }
}
