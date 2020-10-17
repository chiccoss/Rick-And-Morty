package com.sohayb.miniprojet_bahisohayb.Retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetroFitSource {

    private var  retrofit: Retrofit

    private val BASE_URL = "https://rickandmortyapi.com/api/"

    init {
        val okHttpClient = OkHttpClient.Builder().build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    }

    fun getInfoFromInterface(): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}