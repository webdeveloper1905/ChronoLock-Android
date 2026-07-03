package com.yazilim.chronolock

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {

private  const val BASE_URL = "http://10.0.2.2/chronolock_api/"

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())// JSON -> Kotlin dönüşümü için
            .build()

        retrofit.create(ApiService::class.java)
    }
}