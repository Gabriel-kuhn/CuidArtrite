package com.example.gerenciadordetc.network.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {

    companion object {

        fun getRetrofit(): Retrofit {
            val urlServer = "http://34.151.232.143:5000/"
            val gson = GsonBuilder().create()

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(OkHttpClientProvider().getClient()).baseUrl(urlServer)
                .build()
        }
    }
}