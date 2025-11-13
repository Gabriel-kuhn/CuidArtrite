package com.example.cuidartrite.network.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {

    companion object {

        fun getRetrofit(): Retrofit {
            val urlServer = "https://twanda-leery-kimbra.ngrok-free.dev/"
            val gson = GsonBuilder().create()

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(OkHttpClientProvider().getClient()).baseUrl(urlServer)
                .build()
        }
    }
}