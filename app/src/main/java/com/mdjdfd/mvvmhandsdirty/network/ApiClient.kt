package com.mdjdfd.mvvmhandsdirty.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiClient {

    val TAG: String = ApiClient::class.java.simpleName


    fun getApi(): ApiInterface {
        return getClient(getApiUrl()).create(ApiInterface::class.java)
    }


    private fun getApiUrl(): String {
        return BASE_URL + API_VERSION
    }


    private fun getClient(url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS).build()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
