package com.mdjdfd.mvvmhandsdirty.auth

import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.mdjdfd.mvvmhandsdirty.R
import com.mdjdfd.mvvmhandsdirty.ScreenState
import com.mdjdfd.mvvmhandsdirty.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var mViewModel : AuthenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mViewModel = ViewModelProviders.of(
            this,
            LoginViewModelFactory(AuthenticationInteractor())
        )[AuthenticationViewModel::class.java]

        mViewModel.authenticationState.observe(::getLifecycle, ::updateUI)

        button.setOnClickListener { onLoginClicked() }
    }

    private fun updateUI(screenState: ScreenState<AuthenticationState>?) {
        when (screenState) {
            ScreenState.Loading -> progress.visibility = View.VISIBLE
            is ScreenState.Render -> processLoginState(screenState.renderState)
        }
    }

    private fun processLoginState(renderState: AuthenticationState) {
        progress.visibility = View.GONE
        when (renderState) {
            AuthenticationState.Success -> startActivity(Intent(this, MainActivity::class.java))
            AuthenticationState.WrongUserName -> username.error = getString(R.string.username_error)
            AuthenticationState.WrongPassword -> password.error = getString(R.string.password_error)
        }
    }

    private fun onLoginClicked() {
        mViewModel.onLoginClicked(username.text.toString(), password.text.toString())
    }

}
