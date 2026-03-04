package com.example.zoologicoapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // Endpoint principal del zoológico
    private const val BASE_URL = "https://zoo-api.vercel.app/es/"

    fun getRetrofit(): ZooApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ZooApi::class.java)
    }
}