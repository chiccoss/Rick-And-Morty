package com.sohayb.miniprojet_bahisohayb.Retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.ExecutorService
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


class RetroFitSource {

    private var  retrofit: Retrofit

    private val BASE_URL = "https://rickandmortyapi.com/api/"

    init {
        val okHttpClient = OkHttpClient.Builder().build()
        /*val exec: ExecutorService =
            ThreadPoolExecutor(20, 20, 1, TimeUnit.HOURS, LinkedBlockingQueue())
        var disp : Dispatcher = Dispatcher(exec)
        okHttpClient.also {
            it.dispatcher(disp)
            it.build()

        }
        okHttpClient*/


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