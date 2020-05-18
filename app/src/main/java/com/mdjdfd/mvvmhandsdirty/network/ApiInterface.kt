package com.mdjdfd.mvvmhandsdirty.network

import com.mdjdfd.mvvmhandsdirty.model.CurrencyModel

import io.reactivex.Observable
import retrofit2.http.GET


interface ApiInterface {

    @GET("prices?key=a61dd0d39ecfb701d0a3f683a688bb89")
    fun getData(): Observable<List<CurrencyModel>>

}