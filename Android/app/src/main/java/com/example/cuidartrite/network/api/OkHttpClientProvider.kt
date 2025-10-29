package com.example.gerenciadordetc.network.api

import android.util.Log
import com.example.cuidartrite.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class OkHttpClientProvider {

    private var client: OkHttpClient? = null


    // === Interceptor ===============================================================================
    private val interceptorTime = Interceptor { chain: Interceptor.Chain? ->
        val request = chain!!.request()
        val startTime = System.currentTimeMillis()

        val response = chain.proceed(request)

        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        Log.e("OkHttp", "duration: $duration ms")
        response
    }

    // If We want to set some header or token

//    private val interceptorAuth = Interceptor { chain: Interceptor.Chain? ->
//        val requestHeader = chain!!
//            .request()
//            .newBuilder()
//            .addHeader("Authorization", "Bearer " + token)
//            .build()
//        chain.proceed(requestHeader)
//    }


    private fun createBaseBuilder(): OkHttpClient.Builder {
        val loggingInterceptor =
            HttpLoggingInterceptor { message: String? ->
                Log.d("OkHttp", message.toString())
            }

        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        return OkHttpClient.Builder()
            .addInterceptor(interceptorTime)
            .cache(null)
    }


    fun getClient(): OkHttpClient {
        if (client == null) {
            client = createBaseBuilder()
                //.addInterceptor(interceptorAuth)
                .build()
        }
        return client!!
    }
}