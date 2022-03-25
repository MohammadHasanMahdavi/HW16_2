package com.example.hw15_2_2.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://papp.ir/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(ApiInteface::class.java)
}
