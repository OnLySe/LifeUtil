package com.zzq.lifeutil.net

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class LifeRetrofit {

    private val GANK_URL = "https://gank.io/api/v2/"
    private lateinit var gankRetrofit: Retrofit

    lateinit var gankApi: GankApi
        private set

    init {
        initGankApi()
    }

    fun initGankApi() {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .build()
                    return chain.proceed(request)
                }

            }).addInterceptor(LogPrintInterceptor())
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
        gankRetrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(GANK_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        gankApi = gankRetrofit.create(GankApi::class.java)
    }


}