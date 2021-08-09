package com.example.carspree.network

import com.example.carspree.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val retrofitService: ApiService by lazy {
    retrofit.create(ApiService::class.java)
}