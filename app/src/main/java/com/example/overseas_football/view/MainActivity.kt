package com.example.overseas_football.view

import android.content.Context
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.util.Log
import com.example.overseas_football.R
import com.example.overseas_football.databinding.ActivityMainBinding
import com.example.overseas_football.view.fragment.Tab1_Community
import com.example.overseas_football.view.fragment.Tab2_News
import com.example.overseas_football.view.fragment.Tab3_MyProfile
import com.example.overseas_football.viewmodel.MainViewModel
import com.google.android.gms.common.util.ClientLibraryUtils.getPackageInfo
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import android.provider.SyncStateContract.Helpers.update
import android.content.pm.PackageInfo



class MainActivity : BaseActivity() {
    val mainViewModel = MainViewModel()

    val tab1_Community = Tab1_Community()
    val tab2_News = Tab2_News()
    val tab3_MyProfile = Tab3_MyProfile()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.mainActivity = mainViewModel
        binding.bottombar.setOnTabSelectListener { tabId ->
            val transaction = fragmentManager.beginTransaction()
            when (tabId) {
                R.id.tab1_community -> transaction.replace(R.id.framelayout, tab1_Community).commit()
                R.id.tab2_news -> transaction.replace(R.id.framelayout, tab2_News).commit()
                R.id.tab3_myprofile -> transaction.replace(R.id.framelayout, tab3_MyProfile).commit()
            }
        }
    }

    fun getKeyHash(context: Context):String
    {
        val packageInfo = getPackageInfo (context, PackageManager.GET_SIGNATURES.toString())

        for ( signature in packageInfo!!.signatures) {
        try {
            val md = MessageDigest.getInstance ("SHA");
            md.update(signature.toByteArray())
            return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
        } catch ( e: NoSuchAlgorithmException) {

        }
    }
        return ""
    }
}
