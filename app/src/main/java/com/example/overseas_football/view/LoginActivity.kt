package com.example.overseas_football.view

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import com.example.overseas_football.R
import com.example.overseas_football.databinding.ActivityLoginBinding
import com.example.overseas_football.viewmodel.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException
import com.kakao.util.helper.log.Logger
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {
    companion object {
        const val GOOGLE_LOGIN_RESULTCODE = 9001
    }

    private var callback: SessionCallback? = null
    private val loginViewModel = LoginViewModel(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (DataBindingUtil.setContentView(this, R.layout.activity_login) as ActivityLoginBinding).let {
            it.loginViewModel = loginViewModel
        }

        button_google_auth.setOnClickListener {
            startActivityForResult(loginViewModel.GetGoogleSignInClient(this).signInIntent, GOOGLE_LOGIN_RESULTCODE)
        }

        btn_kakao_auth.setOnClickListener {
            callback = SessionCallback()
            Session.getCurrentSession().addCallback(callback)
            Session.getCurrentSession().checkAndImplicitOpen()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(callback)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return
        }
        if (requestCode == GOOGLE_LOGIN_RESULTCODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                Log.e("??", e.message)
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(this, { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = FirebaseAuth.getInstance().currentUser
                        user.let {
                            if (it != null) {
                                loginViewModel.GoogleLogin()
                            }
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                    }
                })
    }

    private inner class SessionCallback : ISessionCallback {

        override fun onSessionOpened() {
            loginViewModel.requestKakaoAuth()
        }

        override fun onSessionOpenFailed(exception: KakaoException?) {
            if (exception != null) {
                Logger.e(exception)
            }
        }
    }
}
