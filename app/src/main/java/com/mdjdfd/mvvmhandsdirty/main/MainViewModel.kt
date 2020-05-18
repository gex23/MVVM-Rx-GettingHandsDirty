package com.mdjdfd.mvvmhandsdirty.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mdjdfd.mvvmhandsdirty.ScreenState
import com.mdjdfd.mvvmhandsdirty.model.CurrencyModel

class MainViewModel(private val loadItemInteractor: LoadItemInteractor) : ViewModel(),
LoadItemInteractor.OnDataLoadListener{


    private lateinit var mMainState: MutableLiveData<ScreenState<MainState>>

    val mainState: LiveData<ScreenState<MainState>>
        get() {
            if (!::mMainState.isInitialized) {
                mMainState = MutableLiveData()
                mMainState.value = ScreenState.Loading
                loadItemInteractor.findItems(this)
            }
            return mMainState
        }


    override fun onError(message: String) {
        //TODO Handle error message
    }

    override fun onComplete() {
        //TODO handle onCompletion
    }

    override fun onSuccess(value: List<CurrencyModel>?) {
        mMainState.value = ScreenState.Render(MainState.ShowItems(value!!))
    }


    fun onItemClicked(item: String) {
        mMainState.value = ScreenState.Render(MainState.ShowMessage(item))
    }
}

class MainViewModelFactory(private val loadItemInteractor: LoadItemInteractor) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(loadItemInteractor) as T
    }
}