package com.mdjdfd.mvvmhandsdirty.main

import com.mdjdfd.mvvmhandsdirty.model.CurrencyModel
import com.mdjdfd.mvvmhandsdirty.network.ApiClient
import com.mdjdfd.mvvmhandsdirty.network.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class LoadItemInteractor {
    private val TAG:String = LoadItemInteractor::class.java.simpleName

    interface OnDataLoadListener {
        fun onError(message: String)
        fun onComplete()
        fun onSuccess(value: List<CurrencyModel>?)
    }


    private var mApiClient: ApiClient = ApiClient()
    private var mApiInterface: ApiInterface = mApiClient.getApi()

    private var mCompositeDisposable: CompositeDisposable? = null

    private var mListener: OnDataLoadListener? = null


    fun findItems(mListener: OnDataLoadListener){
        this.mListener = mListener
        callApi()
    }

    private fun callApi() {
        mCompositeDisposable = CompositeDisposable()

        mCompositeDisposable?.add(
            mApiInterface.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse)
        )
    }

    private fun handleResponse(cryptoList: List<CurrencyModel>) {
        mListener!!.onSuccess(cryptoList)
    }

}