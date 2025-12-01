package com.example.cuidartrite.network.api.service

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ExportService {
    @Headers("Content-Type: text/csv", "Accept: text/csv")
    @GET("download-pains")
    suspend fun downloadPains(): Response<ResponseBody>
}

