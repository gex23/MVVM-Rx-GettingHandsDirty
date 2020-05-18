package com.mdjdfd.mvvmhandsdirty.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mdjdfd.mvvmhandsdirty.ScreenState

class AuthenticationViewModel(private val mAuthenticationInteractor: AuthenticationInteractor) : ViewModel(),
    AuthenticationInteractor.OnLoginFinishedListener {

    private val mAuthenticationState: MutableLiveData<ScreenState<AuthenticationState>> = MutableLiveData()

    val authenticationState: LiveData<ScreenState<AuthenticationState>> get() = mAuthenticationState

    fun onLoginClicked(username: String, password: String) {
        mAuthenticationState.value = ScreenState.Loading
        mAuthenticationInteractor.login(username, password, this)
    }

    override fun onUsernameError() {
        mAuthenticationState.value = ScreenState.Render(AuthenticationState.WrongUserName)
    }

    override fun onPasswordError() {
        mAuthenticationState.value = ScreenState.Render(AuthenticationState.WrongPassword)
    }

    override fun onSuccess() {
        mAuthenticationState.value = ScreenState.Render(AuthenticationState.Success)
    }

}

class LoginViewModelFactory(private val authenticationInteractor: AuthenticationInteractor) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthenticationViewModel(authenticationInteractor) as T
    }
}